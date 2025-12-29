package org.example.kmp.movieapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBackUp
import compose.icons.tablericons.Loader
import org.example.kmp.movieapp.domain.MovieDetailsUiState
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    viewModel: MovieViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val movieDetails by viewModel.movieDetails.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = TablerIcons.ArrowBackUp,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        MovieDetails(
            modifier = modifier.padding(top = paddingValues.calculateTopPadding()),
            movieDetails = movieDetails
        )
    }
}

@Composable
fun MovieDetails(modifier: Modifier = Modifier, movieDetails: MovieDetailsUiState) {

    when (movieDetails.screenState) {
        is ScreenUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = TablerIcons.Loader,
                    contentDescription = "No results found"
                )
            }
        }

        is ScreenUiState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error")
            }
        }

        is ScreenUiState.Success -> {
            if (movieDetails.data == null) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No data found")
                }
            } else {
                val movie = movieDetails.data
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w342/${movie.posterPath}",
                            contentDescription = movie.title,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(text = "Release Year", fontWeight = FontWeight.Bold)
                        Text(
                            text = movie.releaseDate.take(4),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp,
                        )
                        Text(text = "Genres", fontWeight = FontWeight.Bold)
                        movie.genres.forEach {
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}