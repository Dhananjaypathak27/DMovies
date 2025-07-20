package com.pixelveda.dmovies.data.remote

import com.pixelveda.dmovies.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInterface {

    fun getInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): OmdbApi {
        return getInterface().create(OmdbApi::class.java)
    }

}