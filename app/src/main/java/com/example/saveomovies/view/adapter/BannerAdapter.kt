package com.example.saveomovies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saveomovies.databinding.ItemBannerBinding
import com.example.saveomovies.model.movie.Result

class BannerAdapter(
    private val movies: List<Result>,
    private val onClick: (Result) -> Unit
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val movie = movies[position]
        holder.setData(movie)
    }

    override fun getItemCount(): Int =
        movies.size

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(movie: Result) {
            with(binding) {
                setMovie(movie)
                banner.setOnClickListener {
                    onClick.invoke(movie)
                }
            }
        }
    }

}