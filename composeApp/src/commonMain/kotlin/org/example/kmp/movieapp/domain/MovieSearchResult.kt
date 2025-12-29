package org.example.kmp.movieapp.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResult(
    @SerialName("Response")
    val response: String,
    @SerialName("Search")
    val search: List<Search>? = null,
    val totalResults: String
)