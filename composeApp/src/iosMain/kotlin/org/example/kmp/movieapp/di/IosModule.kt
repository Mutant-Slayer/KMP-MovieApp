package org.example.kmp.movieapp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.kmp.movieapp.AppDatabase
import org.koin.dsl.module

val iosModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(AppDatabase.Schema, "movie.db")
    }
}