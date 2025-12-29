package org.example.kmp.movieapp.util

import co.touchlab.kermit.Logger

object AppLogger {
    private val logger = Logger.withTag("MovieApp")

    fun d(message: String, tag: String = "Debug") {
        logger.d { "[$tag] $message" }
    }

    fun i(message: String, tag: String = "Info") {
        logger.i { "[$tag] $message" }
    }

    fun w(message: String, tag: String = "Warning") {
        logger.w { "[$tag] $message" }
    }

    fun e(throwable: Throwable?, message: String, tag: String = "Error") {
        logger.e(throwable) { "[$tag] $message" }
    }

    fun v(message: String, tag: String = "Verbose") {
        logger.v { "[$tag] $message" }
    }
}