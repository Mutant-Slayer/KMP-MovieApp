package org.example.kmp.movieapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import compose.icons.TablerIcons
import compose.icons.tablericons.MoodEmpty
import org.example.kmp.movieapp.domain.SearchUiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = koinViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchUiState by viewModel.searchResult.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(horizontal = 4.dp, vertical = 4.dp),
                value = searchQuery,
                onValueChange = viewModel::setSearchQuery,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(20.dp),
                                color = Color.Gray
                            )
                            .padding(
                                horizontal = 16.dp,
                                vertical = 10.dp
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "Search your movies here",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    ) { paddingValues ->
        MovieLists(
            modifier = modifier.padding(top = paddingValues.calculateTopPadding()),
            searchUiState = searchUiState,
            onClick = { }
        )
    }
}

@Composable
fun MovieLists(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    onClick: (String) -> Unit,
) {
    when (searchUiState.screenState) {
        ScreenUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        ScreenUiState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = searchUiState.errorMessage ?: "An error occurred",
                    color = Color.Red
                )
            }
        }

        ScreenUiState.Success -> {
            val movieList = searchUiState.data

            if (movieList != null) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    movieList.forEach { item ->
                        Card(
                            modifier = Modifier
                                .clickable { onClick(item.imdbID) }
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            border = BorderStroke(width = 1.dp, color = Color.Black),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                AsyncImage(
                                    model = item.poster,
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    contentScale = ContentScale.Crop,
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = "Title : " + item.title)
                                    Text(text = "Released in : " + item.year)
                                }
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = TablerIcons.MoodEmpty,
                        contentDescription = "No results found"
                    )
                }
            }
        }
    }
}