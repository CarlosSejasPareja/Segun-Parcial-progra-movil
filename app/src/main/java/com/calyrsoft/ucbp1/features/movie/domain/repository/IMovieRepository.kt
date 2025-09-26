package com.calyrsoft.ucbp1.features.movie.domain.repository

import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    /**
     * Llama a la API de populares, guarda/upsertea todo en Room,
     * y retorna la lista local ya persistida.
     */
    suspend fun refreshPopular(page: Int = 1): Result<List<MovieModel>>

    /**
     * Observa en tiempo real los cambios en la tabla local.
     */
    fun observePopular(): Flow<List<MovieModel>>
}
