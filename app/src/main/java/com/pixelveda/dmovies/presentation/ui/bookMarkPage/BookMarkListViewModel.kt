package com.pixelveda.dmovies.presentation.ui.bookMarkPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pixelveda.dmovies.common.Resource
import com.pixelveda.dmovies.domain.useCases.GetAllMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update


class BookMarkListViewModel(val context: Application): AndroidViewModel(context) {

    private val getAllMoviesUseCase by lazy {
        GetAllMoviesUseCase(context)
    }

    private val _moviesListState = MutableStateFlow(MovieListState())
    val moviesListState = _moviesListState.asStateFlow()

    init {
        getAllMovies()
    }

    private fun getAllMovies(){
        getAllMoviesUseCase().onEach {
            result ->
                when(result) {
                    is Resource.Success ->{
                        _moviesListState.update { MovieListState(movies = result.data ?: emptyList()) }
                    }
                    is Resource.Error ->{
                        _moviesListState.update { MovieListState(error = result.message ?: "An unexpected error occured") }
                    }
                    is Resource.Loading ->{
                        _moviesListState.update { MovieListState(isLoading = true) }
                    }
                }
        }.launchIn(viewModelScope)
    }



}