package com.eslam.flickers_app.ui.main.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eslam.domain.model.Movie
import com.eslam.domain.model.YearMovies
import com.eslam.flickers_app.databinding.ItemHomeDataBinding

class HomeDataAdapter(
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<HomeDataAdapter.MyViewHolder>() {

    private var years = ArrayList<YearMovies>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<YearMovies>) {
        years.clear()
        years.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemHomeDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(years[position])
    }

    override fun getItemCount(): Int = years.size

    inner class MyViewHolder(private val binding: ItemHomeDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(year: YearMovies) {
            binding.apply {
                tvYear.text = year.year.toString()
                rvMovies.adapter = MoviesAdapter(year.movies) {
                    onItemClick(it)
                }
            }
        }
    }
}