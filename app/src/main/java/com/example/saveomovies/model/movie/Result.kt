package com.example.saveomovies.model.movie

import java.io.Serializable

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Serializable {

    fun getMovieRatingInString(): String {
        return getMovieRating().toString().substring(0..2)
    }

    fun getMovieRating(): Float =
        this.vote_average.toFloat() / 2

}