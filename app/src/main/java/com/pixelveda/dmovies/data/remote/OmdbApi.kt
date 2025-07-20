package com.pixelveda.dmovies.data.remote

import com.pixelveda.dmovies.data.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET(".")
    suspend fun getMovie(
        @Query("t") title:String,
        @Query("apikey") apiKey:String
    ): MovieDto
}