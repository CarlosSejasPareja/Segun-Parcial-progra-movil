package com.calyrsoft.ucbp1.features.movie.data.repository

import com.calyrsoft.ucbp1.features.movie.data.datasource.MoviesLocalDataSource
import com.calyrsoft.ucbp1.features.movie.data.datasource.MoviesRemoteDataSource
import com.calyrsoft.ucbp1.features.movie.data.mapper.toDomain
import com.calyrsoft.ucbp1.features.movie.data.mapper.toEntity
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remote: MoviesRemoteDataSource,
    private val local: MoviesLocalDataSource
) : IMoviesRepository {

    override suspend fun refreshPopular(page: Int): Result<List<MovieModel>> {
        return try {
            val response = remote.getPopularMovies(page)
            response.fold(
                onSuccess = { discoverPage ->
                    val now = System.currentTimeMillis()
                    val dtos = discoverPage.results ?: emptyList()
                    val entities = dtos.map { it.toEntity(now) } // API -> Entity
                    local.upsertAll(entities)                    // guarda/actualiza en Room
                    val localList = local.getAllOnce().map { it.toDomain() } // DB -> domain
                    Result.success(localList)
                },
                onFailure = { ex -> Result.failure(ex) }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun observePopular(): Flow<List<MovieModel>> {
        return local.observeAll().map { list -> list.map { it.toDomain() } }
    }
}
