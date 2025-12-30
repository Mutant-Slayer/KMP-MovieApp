package org.example.kmp.movieapp.util

import org.example.kmp.movieapp.MovieEntity
import org.example.kmp.movieapp.domain.Result

fun Result.toEntity(page: Int): MovieEntity {
    return MovieEntity(
        id = id.toLong(),
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds.joinToString(","),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount.toLong(),
        page = page.toLong()
    )
}

fun MovieEntity.toResult(): Result {
    return Result(
        id = id.toInt(),
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds.split(",").map { it.toInt() },
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount.toInt()
    )
}