package com.pixelveda.dmovies.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pixelveda.dmovies.data.dto.DBMovieDto

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: DBMovieDto)

    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies(): List<DBMovieDto>

    @Delete
    suspend fun deleteMovie(movie: DBMovieDto)
}