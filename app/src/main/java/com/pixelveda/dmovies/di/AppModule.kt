package com.pixelveda.dmovies.di

import android.app.Application
import androidx.room.Room
import com.pixelveda.dmovies.common.Constants
import com.pixelveda.dmovies.data.local.MovieDatabase
import com.pixelveda.dmovies.data.remote.OmdbApi
import com.pixelveda.dmovies.data.repository.MovieRepositoryImpl
import com.pixelveda.dmovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: OmdbApi, db: MovieDatabase): MovieRepository = MovieRepositoryImpl(api,db)

}