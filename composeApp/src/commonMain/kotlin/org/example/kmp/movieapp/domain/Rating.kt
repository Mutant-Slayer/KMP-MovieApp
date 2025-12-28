package org.example.kmp.movieapp.domain

import kotlinx.serialization.SerialName

data class Rating(
    @SerialName("Source")
    val source: String,
    @SerialName("Value")
    val value: String,
)