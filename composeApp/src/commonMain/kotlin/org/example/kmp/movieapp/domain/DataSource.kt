package org.example.kmp.movieapp.domain

sealed class DataSource<out T> {
    data class Network<T>(val data: T) : DataSource<T>()
    data class Cache<T>(val data: T, val isOffline: Boolean = false) : DataSource<T>()
    data class Error(val exception: Throwable) : DataSource<Nothing>()
}