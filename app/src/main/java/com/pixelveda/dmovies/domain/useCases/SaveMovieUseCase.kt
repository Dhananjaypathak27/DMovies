package com.pixelveda.dmovies.domain.useCases

import android.app.Application
import com.pixelveda.dmovies.data.repository.MovieRepositoryImpl
import com.pixelveda.dmovies.domain.model.Movie
import com.pixelveda.dmovies.domain.repository.MovieRepository

class SaveMovieUseCase(context: Application) {

    private val repository: MovieRepository by lazy {
        MovieRepositoryImpl(context)
    }

    suspend operator fun invoke(movie: Movie) {
        repository.saveMoveToDb(movie)
    }

}