package com.pixelveda.dmovies.domain.repository

import com.pixelveda.dmovies.data.dto.MovieDto

interface MovieRepository {
    suspend fun getMovie(title: String, apiKey: String): MovieDto
}