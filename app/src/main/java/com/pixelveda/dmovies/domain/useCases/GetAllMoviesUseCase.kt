package com.pixelveda.dmovies.domain.useCases

import android.app.Application
import android.content.Context
import com.pixelveda.dmovies.common.Resource
import com.pixelveda.dmovies.data.repository.MovieRepositoryImpl
import com.pixelveda.dmovies.domain.model.Movie
import com.pixelveda.dmovies.domain.model.toMovie
import com.pixelveda.dmovies.domain.repository.MovieRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
//    private val repository: MovieRepository by lazy {
//        MovieRepositoryImpl(context)
//    }



    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getAllMoviesFromDb().map { it.toMovie() }
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "couldn't reach server, Check you internet connection"))
        }
    }
}