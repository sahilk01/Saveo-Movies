package com.example.saveomovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.saveomovies.model.Outcome
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.network.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val trendingMovies = liveData {
        emitSource(homeRepository.trendingMovies)
    }

    val popularMoviesFlow = flow {
        emitAll(homeRepository.getPopularMovies().cachedIn(viewModelScope))
    }

    init {
        getTrendingMovies()
    }

    fun getTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getTrendingMovies()
        }
    }

}