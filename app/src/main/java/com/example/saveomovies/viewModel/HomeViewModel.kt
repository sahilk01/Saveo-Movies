package com.example.saveomovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.saveomovies.model.Outcome
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.network.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val trendingMovies = liveData {
        emitSource(homeRepository.trendingMovies)
    }

    fun getTrendingMovies() {
        viewModelScope.launch {
            homeRepository.getTrendingMovies()
        }
    }
}