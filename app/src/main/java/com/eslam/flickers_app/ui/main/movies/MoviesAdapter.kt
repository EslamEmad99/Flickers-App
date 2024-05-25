package com.eslam.flickers_app.ui.main.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eslam.domain.model.Movie
import com.eslam.flickers_app.databinding.ItemMovieBinding

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MyViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                tvMovieTitle.text = movie.title
                tvMovieTitle.isSelected = true
                tvMovieGenres.text = ""
                movie.genres.forEachIndexed { index, genre ->
                    tvMovieGenres.append("$genre ${if (index == movie.genres.size - 1) "" else ", "}")
                }
                tvMovieRatingValue.text = movie.rating.toString()
                root.setOnClickListener {
                    onItemClick(movie)
                }
            }
        }
    }
}