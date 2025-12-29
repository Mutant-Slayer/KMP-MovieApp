package org.example.kmp.movieapp.domain

import org.example.kmp.movieapp.ui.ScreenUiState

data class PopularMoviesUiState(
    val screenState: ScreenUiState = ScreenUiState.Loading,
    val data: PopularMovieList? = null,
    val errorMessage: String? = null
)