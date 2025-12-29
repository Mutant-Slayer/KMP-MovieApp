package org.example.kmp.movieapp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.kmp.movieapp.di.initKoin
import org.example.kmp.movieapp.ui.MovieSearchScreen

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin() // Initialize Koin for iOS
    }
) {
    MovieSearchScreen()
}