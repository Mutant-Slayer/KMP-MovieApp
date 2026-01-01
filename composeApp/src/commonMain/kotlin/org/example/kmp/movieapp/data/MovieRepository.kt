package org.example.kmp.movieapp.data

import kotlinx.coroutines.flow.Flow
import org.example.kmp.movieapp.MovieEntity
import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.RequestResult

interface MovieRepository {
    suspend fun getPopularMovieList(): Flow<RequestResult<List<MovieEntity>>>

    suspend fun getSearchedMovieResult(query: String): RequestResult<List<MovieEntity>>

    suspend fun getMovieDetails(movieId: Int): RequestResult<MovieDetails>
}