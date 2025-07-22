package com.pixelveda.dmovies.presentation.ui.bookMarkPage

import com.pixelveda.dmovies.domain.model.Movie

data class MovieListState (
    val isLoading : Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error : String = ""
)