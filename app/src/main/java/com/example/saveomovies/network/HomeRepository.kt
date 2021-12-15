package com.example.saveomovies.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.saveomovies.R
import com.example.saveomovies.model.Outcome
import com.example.saveomovies.model.movie.Movie
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.util.PER_PAGE_ITEMS
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val tmdbApi: TMDBApi,
    private val popularMoviesPagingSource: PopularMoviesPagingSource,
    @ApplicationContext private val appContext: Context
) {

    private val _trendingMovies = MutableLiveData<Outcome<List<Result>>>()
    val trendingMovies: LiveData<Outcome<List<Result>>>
        get() = _trendingMovies

    suspend fun getTrendingMovies() {
        _trendingMovies.postValue(Outcome.Loading())
        try {
            val response = tmdbApi.getTrendingMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    _trendingMovies.postValue(Outcome.Success(it.results.slice(0..5)))
                }
                    ?: _trendingMovies.postValue(Outcome.Failure(Throwable(appContext.getString(R.string.something_went_wrong))))
            } else {
                _trendingMovies.postValue(Outcome.Failure(Throwable(appContext.getString(R.string.something_went_wrong))))
            }
        } catch (e: Exception) {
            _trendingMovies.postValue(Outcome.Failure(Throwable(appContext.getString(R.string.something_went_wrong_no_internet))))
        }
    }

    fun getPopularMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = PER_PAGE_ITEMS,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                popularMoviesPagingSource
            }
        ).flow
    }

}