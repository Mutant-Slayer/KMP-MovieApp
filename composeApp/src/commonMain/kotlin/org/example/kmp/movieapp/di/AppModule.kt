package org.example.kmp.movieapp.di

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.kmp.movieapp.AppDatabase
import org.example.kmp.movieapp.config.BuildKonfig
import org.example.kmp.movieapp.data.ApiClient
import org.example.kmp.movieapp.data.MovieRepository
import org.example.kmp.movieapp.data.MovieRepositoryImpl
import org.example.kmp.movieapp.ui.MovieViewModel
import org.example.kmp.movieapp.util.AppLogger
import org.example.kmp.movieapp.util.AppLoggerImpl
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.dsl.onClose

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }

            install(DefaultRequest) {
                header("Authorization", "Bearer ${BuildKonfig.MOVIE_API_KEY}")
                header("accept", "application/json")
            }
        }
    } onClose {
        it?.close()
    }
    viewModel { MovieViewModel(get()) }
    single { AppDatabase(get()) }
    single { ApiClient(get(), get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single { Logger.withTag("KMP App") }
    single<AppLogger> { AppLoggerImpl(get()) }
}

fun initKoin(platformModule: org.koin.core.module.Module, config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, platformModule)
    }
}