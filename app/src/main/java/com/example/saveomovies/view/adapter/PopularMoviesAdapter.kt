package com.example.saveomovies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.saveomovies.databinding.ItemPopularMovieBinding
import com.example.saveomovies.model.movie.Result


class PopularMoviesAdapter :
    PagingDataAdapter<Result, PopularMoviesAdapter.PhotoViewHolder>(PhotoDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPopularMovieBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent,
            false
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PhotoViewHolder(private val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result?) {
            binding.movie = item
        }
    }
}

class PhotoDiffCallBack : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}