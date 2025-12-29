package org.example.kmp.movieapp.data

import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.PopularMovieList
import org.example.kmp.movieapp.domain.RequestResult

interface MovieRepository {
    suspend fun getPopularMovieList(): RequestResult<PopularMovieList>

    suspend fun getSearchedMovieResult(query: String): RequestResult<PopularMovieList>

    suspend fun getMovieDetails(movieId: Int): RequestResult<MovieDetails>
}