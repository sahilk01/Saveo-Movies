package com.example.saveomovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.saveomovies.databinding.FragmentHomeBinding
import com.example.saveomovies.model.Outcome
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.util.gone
import com.example.saveomovies.util.handleError
import com.example.saveomovies.util.visible
import com.example.saveomovies.view.adapter.BannerAdapter
import com.example.saveomovies.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
        homeViewModel.getTrendingMovies()
    }

    private fun attachObservers() {
        homeViewModel.trendingMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Outcome.Failure -> handleError(it.throwable.localizedMessage, binding.root)
                is Outcome.Loading -> binding.loader.visible()
                is Outcome.Success -> populateTrendingList(it.data)
            }
        }
    }

    private fun populateTrendingList(movies: List<Result>) {
        binding.loader.gone()
        binding.banner.adapter = BannerAdapter(movies,
            onClick = {
                // TODO: Open Details Page.

            }
        )
    }
}