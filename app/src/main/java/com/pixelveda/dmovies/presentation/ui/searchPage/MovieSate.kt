package com.pixelveda.dmovies.presentation.ui.searchPage

import com.pixelveda.dmovies.domain.model.Movie

data class MovieSate(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error:String = ""
)