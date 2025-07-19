package com.pixelveda.dmovies.presentation.ui.searchPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

private val TAG = "SearchMovieViewModel"
class SearchMovieViewModel: ViewModel() {

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

    private fun searchMovie(query:String){
        Log.e(TAG, "updateText: $query", )
    }

}
