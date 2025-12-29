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
import org.example.kmp.movieapp.domain.RequestResult
import org.example.kmp.movieapp.domain.SearchUiState

@OptIn(FlowPreview::class)
class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _searchResult = MutableStateFlow(SearchUiState())
    val searchResult = _searchResult.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _movieDetails = MutableStateFlow(MovieDetailsUiState())
    val movieDetails = _movieDetails.asStateFlow()

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isNotBlank() && query.length >= 3) {
                        searchMovies(query)
                    } else {
                        _searchResult.update { SearchUiState(ScreenUiState.Success) }
                    }
                }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    fun searchMovies(query: String) {
//        viewModelScope.launch {
//            movieRepository.getSearchedMovieResult(query).cachedIn(viewModelScope)
//                .collect { pagingData ->
//                    _searchResult.update { pagingData }
//                }
//        }
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