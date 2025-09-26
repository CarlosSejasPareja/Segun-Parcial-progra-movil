package com.calyrsoft.ucbp1.features.movie.data.mapper


import com.calyrsoft.ucbp1.features.movie.data.api.dto.MovieDto
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

// API → Entity
fun MovieDto.toEntity(now: Long) = MovieEntity(
    id = 0,
    title = title,
    overview = overview,
    posterPath = poster_path,
    popularity = popularity,
    releaseDate = release_date,
    updatedAt = now
)

// Entity → Domain
fun MovieEntity.toDomain() = MovieModel(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    popularity = popularity,
    releaseDate = releaseDate
)
