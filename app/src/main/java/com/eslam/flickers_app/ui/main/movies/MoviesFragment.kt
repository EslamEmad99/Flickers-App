package com.eslam.flickers_app.ui.main.movies

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eslam.domain.model.Movie
import com.eslam.domain.model.YearMovies
import com.eslam.domain.util.DataState
import com.eslam.domain.util.toJson
import com.eslam.flickers_app.R
import com.eslam.flickers_app.databinding.FragmentMoviesBinding
import com.eslam.flickers_app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()

    private val homeDataAdapter by lazy {
        HomeDataAdapter() {
            navigateToMovieDetailsFragment(movie = it)
        }
    }

    private fun navigateToMovieDetailsFragment(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
            movie = movie.toJson()
        )
        findNavController().navigate(action)
    }

    override fun afterCreateView() {
        initToolbar()
        initSearchView()
        initHomeRecyclerView()
    }

    private fun initToolbar() {
        binding.toolbar.init(getString(R.string.movies))
    }

    private fun initSearchView() {
        binding.layoutSearch.apply {
            etSearch.addTextChangedListener {
                viewModel.getMovies(it.toString())
            }
            etSearch.setOnEditorActionListener { _, _, _ ->
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireView().windowToken, 0)
            }
        }
    }

    private fun initHomeRecyclerView() {
        binding.rvHomeData.adapter = homeDataAdapter
    }

    override fun handleObservers() {
        observe {
            viewModel.getMoviesResponse.collect {
                when (it) {
                    is DataState.Loading -> {
                        progressUtil.statusProgress(true)
                    }

                    is DataState.Success -> {
                        progressUtil.statusProgress(false)
                        setHomeData(it.data)
                    }

                    is DataState.Error -> {
                        progressUtil.statusProgress(false)
                        binding.rvHomeData.isVisible = false
                        binding.layoutNoData.isVisible = true
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setHomeData(years: List<YearMovies>) {
        binding.rvHomeData.isVisible = true
        binding.layoutNoData.isVisible = false
        homeDataAdapter.submitList(years)
    }
}