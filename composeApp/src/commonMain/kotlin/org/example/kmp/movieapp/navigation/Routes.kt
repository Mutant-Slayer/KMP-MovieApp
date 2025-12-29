package org.example.kmp.movieapp.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MovieList : Screen()

    @Serializable
    data class MovieDetails(val movieId: Int) : Screen()
}