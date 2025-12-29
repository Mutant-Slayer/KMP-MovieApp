package org.example.kmp.movieapp.domain

import org.example.kmp.movieapp.ui.ScreenUiState

data class MovieDetailsUiState(
    val screenState: ScreenUiState = ScreenUiState.Loading,
    val data: MovieDetails? = null,
    val errorMessage: String? = null
)