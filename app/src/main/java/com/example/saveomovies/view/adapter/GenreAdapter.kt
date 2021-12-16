package com.example.saveomovies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saveomovies.databinding.ItemGenreBinding
import com.example.saveomovies.model.movieDetail.Genre

class GenreAdapter(
    private val genres: List<Genre>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.setData(genre)
    }

    override fun getItemCount(): Int =
        genres.size

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(genre: Genre) {
            binding.setGenre(genre)
        }
    }

}