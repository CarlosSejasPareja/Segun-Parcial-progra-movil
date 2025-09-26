package com.calyrsoft.ucbp1.features.movie.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDao {

    // Upsert = inserta o actualiza si ya existe misma PK
    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun observeAll(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    suspend fun getAllOnce(): List<MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}
