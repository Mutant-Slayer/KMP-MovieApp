package org.example.kmp.movieapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.kmp.movieapp.AppDatabase
import org.example.kmp.movieapp.MovieEntity
import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.RequestResult
import org.example.kmp.movieapp.domain.toEntityList

class MovieRepositoryImpl(
    private val apiClient: ApiClient,
    private val database: AppDatabase,
) : MovieRepository {
    override suspend fun getPopularMovieList(): Flow<RequestResult<List<MovieEntity>>> = flow {
        emit(RequestResult.Loading)
        val localMovies = database.appDatabaseQueries.selectAllMovies().executeAsList()

        // If we have cached data, show it right away
        if (localMovies.isNotEmpty()) {
            emit(RequestResult.Success(localMovies))
        }

        try {
            // Try to get fresh data from network
            val response = apiClient.getMovieList(1)

            // Clear and Update Database
            database.appDatabaseQueries.transaction {
                database.appDatabaseQueries.deleteAllMovies()
                response.results.forEach { movie ->
                    database.appDatabaseQueries.insertMovie(
                        id = movie.id.toLong(),
                        title = movie.title,
                        overview = movie.overview,
                        posterPath = movie.posterPath,
                        voteAverage = movie.voteAverage,
                        releaseDate = movie.releaseDate,
                        originalLanguage = movie.originalLanguage
                    )
                }
            }
            val freshLocalMovies = database.appDatabaseQueries.selectAllMovies().executeAsList()
            emit(RequestResult.Success(freshLocalMovies))

        } catch (e: Exception) {
            if (localMovies.isEmpty()) {
                emit(RequestResult.Error(e.message ?: "Unknown Error"))
            } else {
                emit(RequestResult.Error("Offline Mode: Showing cached data"))
            }
        }
    }

    override suspend fun getSearchedMovieResult(query: String): RequestResult<List<MovieEntity>> {
        return try {
            val response = apiClient.getSearchedMovieResult(query, 1)
            RequestResult.Success(response.results.toEntityList())
        } catch (e: Exception) {
            RequestResult.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun getMovieDetails(movieId: Int): RequestResult<MovieDetails> {
        return try {
            val response = apiClient.getMovieDetails(movieId)
            RequestResult.Success(response)
        } catch (e: Exception) {
            RequestResult.Error(e.message ?: "Unknown Error")
        }
    }
}