package com.pixelveda.dmovies.presentation.ui.searchPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelveda.dmovies.common.Resource
import com.pixelveda.dmovies.domain.useCases.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

private val TAG = "SearchMovieViewModel"
class SearchMovieViewModel: ViewModel() {

    private val getMovieUseCase by lazy {
        GetMovieUseCase()
    }

    val searchText = MutableStateFlow("")

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
                Log.e(TAG, "searchMovie: ", )
            }
            .onEach {
            result ->
                when(result){
                    is Resource.Success -> {
                        Log.e(TAG, "searchMovie: ${result.data}", )
                    }
                    is Resource.Error -> {
                        Log.e(TAG, "searchMovie: ${result.message}", )
                        }
                    is Resource.Loading -> {
                        Log.e(TAG, "searchMovie: Loading", )
                    }
                }
        }.launchIn(viewModelScope)

    }

}
