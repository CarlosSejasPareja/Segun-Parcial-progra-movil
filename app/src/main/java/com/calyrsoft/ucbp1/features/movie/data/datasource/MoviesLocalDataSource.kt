package com.calyrsoft.ucbp1.features.movie.data.datasource

import com.calyrsoft.ucbp1.features.movie.data.database.dao.IMovieDao
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSource(
    private val dao: IMovieDao
) {
    suspend fun upsertAll(list: List<MovieEntity>) = dao.upsertAll(list)
    fun observeAll() = dao.observeAll()
    suspend fun getAllOnce() = dao.getAllOnce()
    suspend fun clear() = dao.deleteAll()
}
