package com.pixelveda.dmovies.domain.model

import android.os.Parcelable
import com.pixelveda.dmovies.data.dto.DBMovieDto
import kotlinx.parcelize.Parcelize
import com.pixelveda.dmovies.data.dto.MovieDto
import kotlin.String

@Parcelize
data class Movie(
    val id: Int = 0,
    val actors: String?,
    val awards: String?,
    val boxOffice: String?,
    val country: String?,
    val director: String?,
    val genre: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val language: String?,
    val poster: String?,
    val title: String?,
    val runtime: String?,
    val type: String?,
    val writer: String?,
    val year: String?,
    val error: String?,
    val response: String?
): Parcelable

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
        title = title,
        runtime = runtime,
        type = type,
        writer = writer,
        year = year,
        error = error,
        response = response
    )
}

fun Movie.toMovieDBDTO(): DBMovieDto {
    return DBMovieDto(
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
        title = title,
        runtime = runtime,
        type = type,
        writer = writer,
        year = year,
        error = error,
        response = response,
    )
}

fun DBMovieDto.toMovie(): Movie {
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
        title = title,
        runtime = runtime,
        type = type,
        writer = writer,
        year = year,
        error = error,
        response = response
    )
}