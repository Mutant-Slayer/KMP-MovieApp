package org.example.kmp.movieapp

import android.app.Application
import org.example.kmp.movieapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MovieApplication)
        }
    }
}