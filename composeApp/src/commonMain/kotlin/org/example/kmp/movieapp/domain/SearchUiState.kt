package org.example.kmp.movieapp.domain

import org.example.kmp.movieapp.ui.ScreenUiState

data class SearchUiState(
    val screenState: ScreenUiState = ScreenUiState.Loading,
    val data: List<Search>? = null,
    val errorMessage: String? = null
)
