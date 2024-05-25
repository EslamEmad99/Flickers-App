package com.eslam.flickers_app.ui.main.movie_details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elian.gallery_zoom.startFullImageSlider
import com.eslam.domain.model.Flicker
import com.eslam.flickers_app.databinding.ItemMovieImageBinding
import com.eslam.flickers_app.util.loadImageFromUrl

class MovieFlickersAdapter() : RecyclerView.Adapter<MovieFlickersAdapter.MyViewHolder>() {

    private var flickers = ArrayList<Flicker>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Flicker>) {
        flickers.clear()
        flickers.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemMovieImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(flickers[position])
    }

    override fun getItemCount(): Int = flickers.size

    inner class MyViewHolder(private val binding: ItemMovieImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(flicker: Flicker) {
            binding.apply {
                root.loadImageFromUrl(flicker.getImageUrl())
                root.setOnClickListener {
                    startFullImageSlider(
                        itemView.context,
                        ArrayList(flickers.map { it.getImageUrl() }),
                        adapterPosition
                    )
                }
            }
        }

        /**
         * This extension function generates the full URL for a Flickr image based on the Flicker object's properties.
         * It constructs the URL using the farm, server, id, and secret attributes of the Flicker object,
         * following the standard format for accessing images on Flickr's servers.
         * **/
        private fun Flicker.getImageUrl() =
            "https://farm${this.farm}.static.flickr.com/${this.server}/${this.id}_${this.secret}.jpg"
    }
}