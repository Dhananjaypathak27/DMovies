package com.pixelveda.dmovies.presentation.ui.movieDetailPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pixelveda.dmovies.domain.model.Movie
import com.pixelveda.dmovies.domain.useCases.SaveMovieUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(val context: Application) : AndroidViewModel(context) {

    private val saveMovieUseCase by lazy {
        SaveMovieUseCase(context)
    }

    fun saveMovieToDb(movie: Movie){
        viewModelScope.launch {
            saveMovieUseCase(movie)
        }
    }
}