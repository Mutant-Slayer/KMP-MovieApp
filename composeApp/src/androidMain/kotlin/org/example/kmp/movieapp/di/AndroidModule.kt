package org.example.kmp.movieapp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.kmp.movieapp.AppDatabase
import org.koin.dsl.module

val androidModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(AppDatabase.Schema, get(), "movie.db")
    }
}