package com.pixelveda.dmovies.domain.model

import com.pixelveda.dmovies.data.dto.MovieDto
import com.pixelveda.dmovies.data.dto.MovieDto.Rating

data class Movie(
    val actors: String,
    val awards: String,
    val boxOffice: String,
    val country: String,
    val director: String,
    val genre: String,
    val imdbRating: String,
    val imdbVotes: String,
    val language: String,
    val poster: String,
    val ratings: List<Rating>,
    val title: String,
    val runtime: String,
    val type: String,
    val writer: String,
    val year: String
){
    data class Rating(
        val source: String,
        val value: String
    )
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        actors = actors,
        awards = awards,
        boxOffice = boxOffice,
        country = country,
        director = director,
        genre = genre,
        imdbRating = imdbRating,
        imdbVotes = imdbVotes,
        language = language,
        poster = poster,
        ratings = ratings.map { it.toRating() },
        title = title,
        runtime = runtime,
        type = type,
        writer = writer,
        year = year
    )
}

fun Rating.toRating(): Movie.Rating {
    return Movie.Rating(
        source = source,
        value = value
    )
}