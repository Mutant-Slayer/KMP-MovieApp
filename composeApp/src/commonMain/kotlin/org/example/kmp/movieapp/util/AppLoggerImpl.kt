package org.example.kmp.movieapp.util

import co.touchlab.kermit.Logger

class AppLoggerImpl(private val logger: Logger) : AppLogger {
    override fun d(message: String, tag: String) {
        logger.d { "[$tag] $message" }
    }

    override fun i(message: String, tag: String) {
        logger.i { "[$tag] $message" }
    }

    override fun w(message: String, tag: String) {
        logger.w { "[$tag] $message" }
    }

    override fun e(throwable: Throwable?, message: String, tag: String) {
        logger.e(throwable) { "[$tag] $message" }
    }

    override fun v(message: String, tag: String) {
        logger.v { "[$tag] $message" }
    }
}