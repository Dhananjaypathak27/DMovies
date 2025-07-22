package com.pixelveda.dmovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pixelveda.dmovies.data.dto.DBMovieDto

@Database(entities = [DBMovieDto::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}