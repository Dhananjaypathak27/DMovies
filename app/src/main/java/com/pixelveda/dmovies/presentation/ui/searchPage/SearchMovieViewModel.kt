package com.pixelveda.dmovies.presentation.ui.searchPage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelveda.dmovies.common.Resource
import com.pixelveda.dmovies.domain.useCases.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase): ViewModel() {

    private val TAG = "SearchMovieViewModel"


    val searchText = MutableStateFlow("")

    private val _movieState = MutableStateFlow(MovieSate())
    val movieState = _movieState.asStateFlow()

    init {
        viewModelScope.launch {
            searchText.
            filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(1000)
                .collectLatest {
                    searchMovie(it)
                }
        }
    }

    private fun searchMovie(query:String) {
        Log.e(TAG, "updateText: $query",)
        getMovieUseCase(query = query, apiKey = "3d8644fa")
            .onStart {
                _movieState.update { MovieSate(isLoading = true) }
            }
            .onEach {
            result ->
                when(result){
                    is Resource.Success -> {
                        _movieState.update { MovieSate(movie = result.data) }
                    }
                    is Resource.Error -> {
                        _movieState.update { MovieSate(error = result.message ?: "An unexpected error occured") }
                        }
                    is Resource.Loading -> {
                        _movieState.update { MovieSate(isLoading = true) }
                    }
                }
        }.launchIn(viewModelScope)

    }

}
