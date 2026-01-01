package org.example.kmp.movieapp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.kmp.movieapp.di.initKoin
import org.example.kmp.movieapp.di.iosModule

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin(platformModule = iosModule) // Initialize Koin for iOS
    }
) {
    App()
}