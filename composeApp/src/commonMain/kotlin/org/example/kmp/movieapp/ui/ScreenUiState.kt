package org.example.kmp.movieapp.ui

sealed interface ScreenUiState {
    data object Loading : ScreenUiState

    data object Success : ScreenUiState

    data object Error : ScreenUiState
}