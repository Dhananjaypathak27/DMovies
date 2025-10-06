package com.pixelveda.dmovies.domain.useCases

import android.app.Application
import com.pixelveda.dmovies.data.repository.MovieRepositoryImpl
import com.pixelveda.dmovies.domain.model.Movie
import com.pixelveda.dmovies.domain.repository.MovieRepository
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(private val repository: MovieRepository) {

//    private val repository: MovieRepository by lazy {
//        MovieRepositoryImpl(context)
//    }

    suspend operator fun invoke(movie: Movie) {
        repository.saveMoveToDb(movie)
    }

}