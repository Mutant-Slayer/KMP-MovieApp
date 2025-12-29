package org.example.kmp.movieapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.PopularMovieList
import org.example.kmp.movieapp.util.AppLogger

class ApiClient(
    private val client: HttpClient,
    private val logger: AppLogger,
    private val baseUrl: String = "https://api.themoviedb.org",
) {
    suspend fun getMovieList(pageNumber: Int): PopularMovieList {
        logger.d("Fetching movies page")
        return try {
            val result = client.get("$baseUrl/3/movie/popular") {
                parameter("page", pageNumber)
            }.body<PopularMovieList>()

            logger.i("Successfully fetched ${result.results.size} movies")
            result
        } catch (e: Exception) {
            logger.e(e, "Failed to fetch movies page: $pageNumber")
            throw e
        }
    }

    suspend fun getSearchedMovieResult(
        query: String,
        pageNumber: Int,
    ): PopularMovieList {
        logger.d("Searching movies: '$query'")
        return try {
            val result = client.get("$baseUrl/3/search/movie") {
                parameter("query", query)
                parameter("page", pageNumber)
            }.body<PopularMovieList>()

            logger.i("Found ${result.results.size} movies for query: '$query'")
            result
        } catch (e: Exception) {
            logger.e(e, "Search failed for query: '$query'")
            throw e
        }
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        logger.d("Fetching movie details: $movieId")
        return try {
            client.get("$baseUrl/3/movie/$movieId").body<MovieDetails>().also {
                logger.i("Successfully fetched details for: ${it.title}")
            }
        } catch (e: Exception) {
            logger.e(e, "Failed to fetch movie details: $movieId")
            throw e
        }
    }
}