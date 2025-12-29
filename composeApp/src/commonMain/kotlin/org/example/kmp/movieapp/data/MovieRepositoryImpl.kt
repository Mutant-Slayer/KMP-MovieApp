package org.example.kmp.movieapp.data

import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.MovieSearchResult
import org.example.kmp.movieapp.domain.PopularMovieList
import org.example.kmp.movieapp.domain.RequestResult

class MovieRepositoryImpl(
    private val apiClient: ApiClient
) : MovieRepository {
    override suspend fun getPopularMovieList(): RequestResult<PopularMovieList> {
        return try {
            val response = apiClient.getMovieList(1)
            RequestResult.Success(response)
        } catch (e: Exception) {
            RequestResult.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun getSearchedMovieResult(query: String): RequestResult<MovieSearchResult> {
        return try {
            val response = apiClient.getSearchedMovieResult(query, 1)
            RequestResult.Success(response)
        } catch (e: Exception) {
            RequestResult.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun getMovieDetails(imdbId: String): RequestResult<MovieDetails> {
        return try {
            val response = apiClient.getMovieDetails(imdbId)
            RequestResult.Success(response)
        } catch (e: Exception) {
            RequestResult.Error(e.message ?: "Unknown Error")
        }
    }
}