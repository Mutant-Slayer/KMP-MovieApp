package org.example.kmp.movieapp.data

import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.RequestResult
import org.example.kmp.movieapp.domain.Search

interface MovieRepository {
    suspend fun getSearchedMovieResult(query: String): RequestResult<Search>

    suspend fun getMovieDetails(imdbId: String): RequestResult<MovieDetails>
}