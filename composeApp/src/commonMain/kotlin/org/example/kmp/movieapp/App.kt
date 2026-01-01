package org.example.kmp.movieapp

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import my.connectivity.kmp.data.model.NetworkStatus
import my.connectivity.kmp.rememberNetworkStatus
import org.example.kmp.movieapp.navigation.Screen
import org.example.kmp.movieapp.ui.MovieDetailScreen
import org.example.kmp.movieapp.ui.MovieListScreen

@Composable
fun App() {
    val navController = rememberNavController()
    val networkStatus by rememberNetworkStatus()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(networkStatus) {
        when (networkStatus) {
            NetworkStatus.NoInternet, NetworkStatus.Lost -> {
                snackbarHostState.showSnackbar(
                    message = "No internet connection. Using offline data.",
                    duration = SnackbarDuration.Indefinite
                )
            }

            NetworkStatus.Available -> {
                snackbarHostState.currentSnackbarData?.dismiss()
            }

            else -> Unit
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.MovieList,
        ) {
            composable<Screen.MovieList> {
                MovieListScreen(
                    onMovieClick = { movieId ->
                        navController.navigate(Screen.MovieDetails(movieId))
                    }
                )
            }

            composable<Screen.MovieDetails> { backStackEntry ->
                val movieDetails: Screen.MovieDetails = backStackEntry.toRoute()
                MovieDetailScreen(
                    movieId = movieDetails.movieId,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}