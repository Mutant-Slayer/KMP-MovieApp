package org.example.kmp.movieapp.data

import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.RequestResult
import org.example.kmp.movieapp.domain.Search

class MovieRepositoryImpl(
    private val apiClient: ApiClient
) : MovieRepository {

    override suspend fun getSearchedMovieResult(query: String): RequestResult<Search> {
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