package org.example.kmp.movieapp.domain

import org.example.kmp.movieapp.MovieEntity

fun Result.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id.toLong(),
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        releaseDate = this.releaseDate,
        originalLanguage = this.originalLanguage
    )
}

fun List<Result>.toEntityList(): List<MovieEntity> {
    return this.map { it.toEntity() }
}