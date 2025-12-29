package org.example.kmp.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.kmp.movieapp.navigation.Screen
import org.example.kmp.movieapp.ui.MovieDetailScreen
import org.example.kmp.movieapp.ui.MovieListScreen

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MovieList
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