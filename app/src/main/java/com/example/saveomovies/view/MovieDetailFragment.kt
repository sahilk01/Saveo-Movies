package com.example.saveomovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.saveomovies.R
import com.example.saveomovies.databinding.FragmentMovieDetailBinding
import com.example.saveomovies.model.movieDetail.Genre
import com.example.saveomovies.util.showSnackBar
import com.example.saveomovies.view.adapter.GenreAdapter
import com.example.saveomovies.viewModel.MovieDetailViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.viewModel = movieDetailViewModel
        binding.handler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailViewModel.setMovieDetails(args.movie)

        val list = listOf(
            Genre(
                1, "Action"
            ),
            Genre(
                1, "DRAMA"
            ),
            Genre(
                1, "COMEDY"
            ),
            Genre(
                1, "THRILLER"
            ),
        )
        showGenres(list)

    }

    private fun showGenres(genres: List<Genre>) {
        val adapter = GenreAdapter(genres)
        binding.genres.adapter = adapter
    }

    fun bookNow() {
        showSnackBar("Booking Successful", binding.root)
    }
}