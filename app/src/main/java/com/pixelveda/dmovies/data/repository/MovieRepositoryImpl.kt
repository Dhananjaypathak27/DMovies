package com.pixelveda.dmovies.data.repository

import com.pixelveda.dmovies.data.dto.MovieDto
import com.pixelveda.dmovies.data.remote.RetrofitInterface
import com.pixelveda.dmovies.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {

    val repository by lazy {
        RetrofitInterface.getApiService()
    }

    override suspend fun getMovie(
        title: String,
        apiKey: String
    ): MovieDto {
        return repository.getMovie(title,apiKey)
    }
}