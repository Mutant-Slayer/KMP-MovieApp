package org.example.kmp.movieapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.PopularMovieList

class ApiClient(
    private val client: HttpClient,
    private val baseUrl: String = "https://api.themoviedb.org",
) {
    suspend fun getMovieList(
        pageNumber: Int
    ): PopularMovieList {
        return client.get("$baseUrl/3/movie/popular") {
            parameter("page", pageNumber)
        }.body()
    }

    suspend fun getSearchedMovieResult(
        query: String,
        pageNumber: Int,
    ): PopularMovieList {
        return client.get("$baseUrl/3/search/movie") {
            parameter("query", query)
            parameter("page", pageNumber)
        }.body()
    }

    suspend fun getMovieDetails(
        movieId: Int,
    ): MovieDetails {
        return client.get("$baseUrl/3/movie/${movieId}") {
        }.body()
    }
}