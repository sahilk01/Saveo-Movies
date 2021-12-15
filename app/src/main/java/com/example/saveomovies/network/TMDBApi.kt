package com.example.saveomovies.network

import com.example.saveomovies.BuildConfig
import com.example.saveomovies.model.movie.Movie
import com.example.saveomovies.model.movie.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    /**
    Returns list of trending movies.
     */

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<Movie>

    /**
    Returns list of popular movies.
     */

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int
    ): Response<Movie>


}