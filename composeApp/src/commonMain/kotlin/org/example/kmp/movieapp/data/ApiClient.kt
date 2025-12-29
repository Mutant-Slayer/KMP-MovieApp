package org.example.kmp.movieapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.Search

class ApiClient(private val baseUrl: String = "https://www.omdbapi.com") {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
    }

    suspend fun getSearchedMovieResult(
        query: String,
        pageNumber: Int,
        apiKey: String = "b75e96af"
    ): Search {
        return client.get(baseUrl) {
            parameter("s", query)
            parameter("page", pageNumber)
            parameter("apikey", apiKey)
        }.body()
    }

    suspend fun getMovieDetails(
        imdbId: String,
        apiKey: String = "b75e96af"
    ): MovieDetails {
        return client.get(baseUrl) {
            parameter("i", imdbId)
            parameter("apikey", apiKey)
        }.body()
    }

    fun close() {
        client.close()
    }
}