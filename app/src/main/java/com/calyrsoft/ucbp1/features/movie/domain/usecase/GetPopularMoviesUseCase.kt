package com.calyrsoft.ucbp1.features.movie.domain.usecase

import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val repository: IMoviesRepository
) {
    // Refresca desde API y guarda en DB
    suspend operator fun invoke(page: Int = 1): Result<List<MovieModel>> =
        repository.refreshPopular(page)

    // Observa en tiempo real desde DB
    fun observe(): Flow<List<MovieModel>> = repository.observePopular()
}
