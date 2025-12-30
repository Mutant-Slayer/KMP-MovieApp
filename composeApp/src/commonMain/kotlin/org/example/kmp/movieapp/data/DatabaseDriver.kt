package org.example.kmp.movieapp.data

import app.cash.sqldelight.db.SqlDriver
import org.example.kmp.movieapp.AppDatabase

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DatabaseDriverFactory): AppDatabase {
    val driver = driverFactory.createDriver()
    return AppDatabase(driver)
}