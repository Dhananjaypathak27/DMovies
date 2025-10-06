package com.pixelveda.dmovies.presentation.ui.movieDetailPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelveda.dmovies.domain.model.Movie
import com.pixelveda.dmovies.domain.useCases.SaveMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val saveMovieUseCase: SaveMovieUseCase) : ViewModel() {

//    private val saveMovieUseCase by lazy {
//        SaveMovieUseCase(context)
//    }

    fun saveMovieToDb(movie: Movie){
        viewModelScope.launch {
            saveMovieUseCase(movie)
        }
    }
}