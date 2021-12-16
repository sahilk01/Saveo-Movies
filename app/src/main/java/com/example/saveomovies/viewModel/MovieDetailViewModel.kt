package com.example.saveomovies.viewModel

import androidx.lifecycle.*
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.network.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {

    private val _movieDetail = MutableLiveData<Result>()
    val movieDetail: LiveData<Result>
        get() = _movieDetail

    val movieDetailsFromNetwork = liveData {
        emitSource(movieDetailRepository.movieDetail)
    }

    fun setMovieDetails(movie: Result) {
        _movieDetail.value = movie
        getMovieDetail(movie.id)
    }

    private fun getMovieDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailRepository.getMovieDetails(id)
        }
    }

}