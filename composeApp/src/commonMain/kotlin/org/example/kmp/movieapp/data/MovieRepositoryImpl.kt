package org.example.kmp.movieapp.data

import org.example.kmp.movieapp.AppDatabase
import org.example.kmp.movieapp.MovieEntity
import org.example.kmp.movieapp.domain.DataSource
import org.example.kmp.movieapp.domain.MovieDetails
import org.example.kmp.movieapp.domain.PopularMovieList
import org.example.kmp.movieapp.domain.RequestResult
import org.example.kmp.movieapp.util.toEntity
import org.example.kmp.movieapp.util.toResult

class MovieRepositoryImpl(
    private val apiClient: ApiClient,
    database: AppDatabase,
) : MovieRepository {
    private val movieQueries = database.appDatabaseQueries

    override suspend fun getPopularMovieList(): RequestResult<PopularMovieList> {
        return try {
            val response = apiClient.getMovieList(1)
            replaceCache(response, 1)
            RequestResult.Success(response)
        } catch (e: Exception) {
            val cachedMovies = getCachedMovies(1)
            if (cachedMovies.isNotEmpty()) {
                DataSource.Cache(
                    data = PopularMovieList(
                        page = 1,
                        results = cachedMovies.map { it.toResult() },
                        totalPages = 500,
                        totalResults = 10000
                    ),
                    isOffline = true
                )
            } else {
                DataSource.Error(e)
            }
            RequestResult.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun getSearchedMovieResult(query: String): RequestResult<PopularMovieList> {
        return try {
            val response = apiClient.getSearchedMovieResult(query, 1)
            RequestResult.Success(response)
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

    private fun getCachedMovies(page: Int): List<MovieEntity> {
        return movieQueries.getMoviesByPage(page.toLong()).executeAsList()
    }

    private fun replaceCache(movieList: PopularMovieList, page: Int) {
        movieQueries.transaction {
            movieQueries.deleteByPage(page.toLong())

            movieList.results.forEach { movie ->
                val entity = movie.toEntity(page)
                movieQueries.insertOrReplace(
                    id = entity.id,
                    adult = entity.adult,
                    backdropPath = entity.backdropPath,
                    genreIds = entity.genreIds,
                    originalLanguage = entity.originalLanguage,
                    originalTitle = entity.originalTitle,
                    overview = entity.overview,
                    popularity = entity.popularity,
                    posterPath = entity.posterPath,
                    releaseDate = entity.releaseDate,
                    title = entity.title,
                    video = entity.video,
                    voteAverage = entity.voteAverage,
                    voteCount = entity.voteCount,
                    page = entity.page,
                )
            }
        }
    }
}