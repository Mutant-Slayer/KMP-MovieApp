package org.example.kmp.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.kmp.movieapp.data.MovieRepository
import org.example.kmp.movieapp.domain.MovieDetailsUiState
import org.example.kmp.movieapp.domain.PopularMoviesUiState
import org.example.kmp.movieapp.domain.RequestResult

@OptIn(FlowPreview::class)
class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _searchResult = MutableStateFlow(PopularMoviesUiState())
    val searchResult = _searchResult.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _movieDetails = MutableStateFlow(MovieDetailsUiState())
    val movieDetails = _movieDetails.asStateFlow()

    private val _popularMovies = MutableStateFlow(PopularMoviesUiState())
    val popularMovies = _popularMovies.asStateFlow()

    init {
        getMovieList()
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isNotBlank() && query.length >= 3) {
                        searchMovies(query)
                    } else {
                        _searchResult.update { PopularMoviesUiState(ScreenUiState.Success) }
                    }
                }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    fun getMovieList() {
        _popularMovies.update { it.copy(screenState = ScreenUiState.Loading) }
        viewModelScope.launch {
            when (val result = movieRepository.getPopularMovieList()) {
                is RequestResult.Success -> {
                    _popularMovies.update {
                        it.copy(
                            screenState = ScreenUiState.Success,
                            data = result.data
                        )
                    }
                }

                is RequestResult.Error -> {
                    _popularMovies.update {
                        it.copy(
                            screenState = ScreenUiState.Error,
                            errorMessage = result.message
                        )
                    }
                }

                else -> {}
            }
        }
    }

    fun searchMovies(query: String) {
        _searchResult.update { it.copy(screenState = ScreenUiState.Loading) }
        viewModelScope.launch {
            when (val result = movieRepository.getSearchedMovieResult(query)) {
                is RequestResult.Success -> {
                    _searchResult.update {
                        it.copy(
                            screenState = ScreenUiState.Success,
                            data = result.data
                        )
                    }
                }

                is RequestResult.Error -> {
                    _searchResult.update {
                        it.copy(
                            screenState = ScreenUiState.Error,
                            errorMessage = result.message
                        )
                    }
                }

                else -> {}
            }
        }
    }

    fun getMovieDetails(imdbId: String) {
        _movieDetails.update { it.copy(screenState = ScreenUiState.Loading) }
        viewModelScope.launch {
            when (val result = movieRepository.getMovieDetails(imdbId)) {
                is RequestResult.Success -> {
                    _movieDetails.update {
                        it.copy(
                            screenState = ScreenUiState.Success,
                            data = result.data
                        )
                    }
                }

                is RequestResult.Error -> {
                    _movieDetails.update {
                        it.copy(
                            screenState = ScreenUiState.Error,
                            errorMessage = result.message
                        )
                    }
                }

                else -> {}
            }
        }
    }
}