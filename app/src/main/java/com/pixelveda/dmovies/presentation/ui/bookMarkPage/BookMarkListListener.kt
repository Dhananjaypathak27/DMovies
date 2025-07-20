package com.pixelveda.dmovies.presentation.ui.bookMarkPage

interface BookMarkListListener {
    fun onItemClick(pos:Int)
}

interface BookMarkListDeleteListener {
    fun onDeleteIconClick(pos:Int)
}