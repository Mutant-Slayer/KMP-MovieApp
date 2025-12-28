package org.example.kmp.movieapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform