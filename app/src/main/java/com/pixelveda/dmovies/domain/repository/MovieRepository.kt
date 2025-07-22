package com.pixelveda.dmovies.domain.repository

import com.pixelveda.dmovies.data.dto.DBMovieDto
import com.pixelveda.dmovies.data.dto.MovieDto
import com.pixelveda.dmovies.domain.model.Movie

interface MovieRepository {
    suspend fun getMovie(title: String, apiKey: String): MovieDto

    suspend fun saveMoveToDb(movie: Movie)

    suspend fun getAllMoviesFromDb(): List<DBMovieDto>
}