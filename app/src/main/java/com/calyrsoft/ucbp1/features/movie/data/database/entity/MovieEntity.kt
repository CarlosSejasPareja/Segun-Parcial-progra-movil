package com.calyrsoft.ucbp1.features.movie.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Long,          // id de TMDB
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val popularity: Double?,
    val releaseDate: String?,
    val updatedAt: Long               // para saber cuándo refrescamos
)
