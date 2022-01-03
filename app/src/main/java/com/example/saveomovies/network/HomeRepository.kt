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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val tmdbApi: TMDBApi,
    private val popularMoviesPagingSource: PopularMoviesPagingSource,
    @ApplicationContext private val appContext: Context
) {

//    private val _trendingMovies = MutableLiveData<Outcome<List<Result>>>()
////    val trendingMovies: LiveData<Outcome<List<Result>>>
////        get() = _trendingMovies

    fun getTrendingMovies(): Flow<Movie> {
        return tmdbApi.getTrendingMovies().flowOn(Dispatchers.IO)
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