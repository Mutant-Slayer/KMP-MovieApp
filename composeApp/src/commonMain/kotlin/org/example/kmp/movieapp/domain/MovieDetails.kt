package org.example.kmp.movieapp.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    @SerialName("Title")
    val title: String,
    @SerialName("Year")
    val year: String,
    @SerialName("Rated")
    val rated: String,
    @SerialName("Released")
    val released: String,
    @SerialName("Runtime")
    val runtime: String,
    @SerialName("Genre")
    val genre: String,
    @SerialName("Director")
    val director: String,
    @SerialName("Writer")
    val writer: String,
    @SerialName("Actors")
    val actors: String,
    @SerialName("Plot")
    val plot: String,
    @SerialName("Language")
    val language: String,
    @SerialName("Country")
    val country: String,
    @SerialName("Awards")
    val awards: String,
    @SerialName("Poster")
    val poster: String,
    @SerialName("Ratings")
    val ratings: List<Rating>,
    @SerialName("Metascore")
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    @SerialName("imdbID")
    val imdbId: String,
    @SerialName("Type")
    val type: String,
    @SerialName("DVD")
    val dvd: String,
    @SerialName("BoxOffice")
    val boxOffice: String,
    @SerialName("Production")
    val production: String,
    @SerialName("Website")
    val website: String,
    @SerialName("Response")
    val response: String,
)