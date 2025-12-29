package org.example.kmp.movieapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.kmp.movieapp.config.BuildKonfig
import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.PopularMovieList

class ApiClient(
    private val baseUrl: String = "https://api.themoviedb.org",
) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }

        install(DefaultRequest) {
            header("Authorization", "Bearer ${BuildKonfig.MOVIE_API_KEY}")
            header("accept", "application/json")
        }
    }

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
        imdbId: String,
    ): MovieDetails {
        return client.get(baseUrl) {
            parameter("i", imdbId)
        }.body()
    }

    fun close() {
        client.close()
    }
}