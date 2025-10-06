package com.pixelveda.dmovies.data.repository

import android.app.Application
import android.content.Context
import com.pixelveda.dmovies.data.dto.DBMovieDto
import com.pixelveda.dmovies.data.dto.MovieDto
import com.pixelveda.dmovies.data.local.LocalDBInterface
import com.pixelveda.dmovies.data.local.MovieDatabase
import com.pixelveda.dmovies.data.remote.OmdbApi
import com.pixelveda.dmovies.data.remote.RetrofitInterface
import com.pixelveda.dmovies.domain.model.Movie
import com.pixelveda.dmovies.domain.model.toMovieDBDTO
import com.pixelveda.dmovies.domain.repository.MovieRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val remoteRepository: OmdbApi,
    val localRepository: MovieDatabase
) : MovieRepository {

    override suspend fun getMovie(
        title: String,
        apiKey: String
    ): MovieDto {
        return remoteRepository.getMovie(title,apiKey)
    }

    override suspend fun saveMoveToDb(movie: Movie) {
        localRepository.movieDao().insertMovie(movie.toMovieDBDTO())
    }

    override suspend fun getAllMoviesFromDb(): List<DBMovieDto> {
        return localRepository.movieDao().getAllMovies()
    }
}