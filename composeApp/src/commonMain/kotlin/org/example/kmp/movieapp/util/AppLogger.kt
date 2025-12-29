package org.example.kmp.movieapp.util

interface AppLogger {
    fun d(message: String, tag: String = "Debug")
    fun i(message: String, tag: String = "Info")
    fun w(message: String, tag: String = "Warning")
    fun e(throwable: Throwable? = null, message: String, tag: String = "Error")
    fun v(message: String, tag: String = "Verbose")
}