package com.pixelveda.dmovies.data.dto

import android.os.Parcelable
import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
@TypeConverters(Converters::class)
data class DBMovieDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val actors: String?,
    val awards: String?,
    val boxOffice: String?,
    val country: String?,
    val director: String?,
    val genre: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val language: String?,
    val poster: String?,
    val title: String?,
    val runtime: String?,
    val type: String?,
    val writer: String?,
    val year: String?,
    val error: String?,
    val response: String?
): Parcelable