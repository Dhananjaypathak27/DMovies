package com.pixelveda.dmovies.data.local

import android.content.Context
import androidx.room.Room

object LocalDBInterface {
    @Volatile
    private var INSTANCE: MovieDatabase? = null

    fun get(context: Context): MovieDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}