package org.example.kmp.movieapp.di

import org.example.kmp.movieapp.data.ApiInterface
import org.example.kmp.movieapp.data.MovieRepository
import org.example.kmp.movieapp.data.MovieRepositoryImpl
import org.example.kmp.movieapp.ui.MovieViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MovieViewModel)
    single { ApiInterface() }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}