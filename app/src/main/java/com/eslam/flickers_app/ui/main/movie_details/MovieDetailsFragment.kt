package com.eslam.flickers_app.ui.main.movie_details

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.eslam.domain.model.Flicker
import com.eslam.domain.model.Movie
import com.eslam.domain.util.fromJson
import com.eslam.flickers_app.R
import com.eslam.flickers_app.databinding.FragmentMovieDetailsBinding
import com.eslam.flickers_app.ui.base.BaseFragment
import com.eslam.flickers_app.ui.base.applyCommonSideEffects
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) {

    private val args by navArgs<MovieDetailsFragmentArgs>()

    private val viewModel by viewModels<MovieDetailsViewModel>()

    private val flickerAdapter by lazy { MovieFlickersAdapter() }

    override fun afterCreateView() {
        initToolbar()
        setMovieDetails()
        initFlickersRV()
        getMovieFlickers()
    }

    private fun initToolbar() {
        binding.toolbar.init(getString(R.string.movie_details))
    }

    private fun setMovieDetails() {
        val movie = args.movie.fromJson<Movie>()
        binding.apply {
            tvMovieNameValue.text = movie.title
            tvMovieYearValue.text = "${movie.year}"
            setMovieGenres(geners = movie.genres)
            setMovieCast(cast = movie.cast)
        }
    }

    private fun setMovieGenres(geners: List<String>) {
        binding.tvMovieGenersValue.text = ""
        geners.forEachIndexed { index, genre ->
            binding.tvMovieGenersValue.append("$genre ${if (index == geners.size - 1) "" else ", "}")
        }
    }

    private fun setMovieCast(cast: List<String>) {
        binding.tvMovieCastValue.text = ""
        cast.forEachIndexed { index, genre ->
            binding.tvMovieCastValue.append("$genre ${if (index == cast.size - 1) "" else ", "}")
        }
    }

    private fun initFlickersRV() {
        binding.rvFlickers.adapter = flickerAdapter
    }

    private fun getMovieFlickers() {
        viewModel.getMovieDetails(movieTitle = args.movie.fromJson<Movie>().title)
    }

    override fun handleObservers() {
        observe {
            viewModel.getMovieDetailsResponse.collect {
                it.applyCommonSideEffects(this) { response ->
                    response.flickersPhotosData?.let { data ->
                        setMovieFlickers(data = data.photo)
                    }
                }
            }
        }
    }

    private fun setMovieFlickers(data: List<Flicker>?) {
        binding.rvFlickers.isGone = data.isNullOrEmpty()
        binding.layoutNoData.isVisible = data.isNullOrEmpty()
        data?.let {
            flickerAdapter.submitList(it)
            binding.rvFlickers.scheduleLayoutAnimation()
        }
    }
}