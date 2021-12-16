package com.example.saveomovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.saveomovies.databinding.FragmentHomeBinding
import com.example.saveomovies.model.Outcome
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.util.gone
import com.example.saveomovies.util.handleError
import com.example.saveomovies.util.visible
import com.example.saveomovies.view.adapter.BannerAdapter
import com.example.saveomovies.view.adapter.PopularMoviesAdapter
import com.example.saveomovies.viewModel.HomeViewModel
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private var adapter: PopularMoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
        }
        adapter = PopularMoviesAdapter(onItemClick = { movie ->
            navigateToDetails(movie)
        })
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        attachObservers()
        getPopularMovies()
    }

    private fun initViews() {
        with(binding!!) {
            if (this.popularRecyclerView.adapter == null) {
                this.popularRecyclerView.adapter = adapter
            }
        }
    }

    private fun getPopularMovies() {

        lifecycleScope.launch {
            homeViewModel.popularMoviesFlow.collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    private fun attachObservers() {
        homeViewModel.trendingMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Outcome.Failure -> handleError(it.throwable.localizedMessage, binding!!.root)
                is Outcome.Loading -> binding!!.loader.visible()
                is Outcome.Success -> populateTrendingList(it.data)
            }
        }
    }

    private fun populateTrendingList(movies: List<Result>) {
        binding!!.loader.gone()
        with(binding!!.banner) {
            setItemTransformer(
                ScaleTransformer.Builder()
                    .setMinScale(0.8f)
                    .build()
            )
            val wrapper = InfiniteScrollAdapter
                .wrap(BannerAdapter(movies, onClick = { movie ->
                    navigateToDetails(movie)
                }))

            adapter = wrapper
        }
    }

    private fun navigateToDetails(movie: Result) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragment2ToMovieDetailFragment(
                movie
            )
        )
    }
}