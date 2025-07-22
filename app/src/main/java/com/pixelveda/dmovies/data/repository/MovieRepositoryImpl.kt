package com.pixelveda.dmovies.data.repository

import android.app.Application
import com.pixelveda.dmovies.data.dto.DBMovieDto
import com.pixelveda.dmovies.data.dto.MovieDto
import com.pixelveda.dmovies.data.local.LocalDBInterface
import com.pixelveda.dmovies.data.remote.RetrofitInterface
import com.pixelveda.dmovies.domain.model.Movie
import com.pixelveda.dmovies.domain.model.toMovieDBDTO
import com.pixelveda.dmovies.domain.repository.MovieRepository

class MovieRepositoryImpl(private val appContext:Application) : MovieRepository {

    val remoteRepository by lazy {
        RetrofitInterface.getApiService()
    }
    val localRepository by lazy {
        LocalDBInterface.get(context = appContext)
    }

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