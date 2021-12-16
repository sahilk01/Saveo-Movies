package com.example.saveomovies.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.saveomovies.R
import com.example.saveomovies.model.Outcome
import com.example.saveomovies.model.movieDetail.MovieDetail
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val tmdbApi: TMDBApi,
    @ApplicationContext private val appContext: Context
) {

    private val _movieDetail = MutableLiveData<Outcome<MovieDetail>>()
    val movieDetail: LiveData<Outcome<MovieDetail>>
        get() = _movieDetail

    suspend fun getMovieDetails(id: Int) {
        _movieDetail.postValue(Outcome.Loading())f
        try {
            val response = tmdbApi.getMovieDetail(id = id)
            if (response.isSuccessful) {
                response.body()?.let {
                    _movieDetail.postValue(Outcome.Success(it))
                }
                    ?: _movieDetail.postValue(Outcome.Failure(Throwable(appContext.getString(R.string.something_went_wrong))))
            } else {
                _movieDetail.postValue(Outcome.Failure(Throwable(appContext.getString(R.string.something_went_wrong))))
            }
        } catch (e: Exception) {
            _movieDetail.postValue(Outcome.Failure(Throwable(appContext.getString(R.string.something_went_wrong_no_internet))))
        }
    }

}