package com.example.saveomovies.network

import com.example.saveomovies.BuildConfig
import com.example.saveomovies.model.movie.Movie
import com.example.saveomovies.model.movie.Result
import com.example.saveomovies.model.movieDetail.MovieDetail
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    /**
    Returns list of trending movies.
     */

    @GET("trending/movie/day")
    fun getTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Flow<Movie>

    /**
    Returns list of popular movies.
     */

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int
    ): Response<Movie>

    /**
    Returns details of the passed ID.
     */

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<MovieDetail>


}