package com.example.saveomovies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saveomovies.model.movie.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor() : ViewModel() {
    private val _movieDetail = MutableLiveData<Result>()
    val movieDetail: LiveData<Result>
        get() = _movieDetail

    fun setMovieDetails(movie: Result) {
        _movieDetail.value = movie
    }
}