package com.pixelveda.dmovies.presentation.ui.bookMarkPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelveda.dmovies.common.Resource
import com.pixelveda.dmovies.domain.useCases.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BookMarkListViewModel @Inject constructor(private val getAllMoviesUseCase: GetAllMoviesUseCase): ViewModel() {

//    private val getAllMoviesUseCase by lazy {
//        GetAllMoviesUseCase(context = application)
//    }

//    @Inject
//    private lateinit var getAllMoviesUseCase: GetAllMoviesUseCase

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