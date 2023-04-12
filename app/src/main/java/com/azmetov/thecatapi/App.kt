package com.azmetov.thecatapi

import android.app.Application
import com.azmetov.thecatapi.di.baseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                baseModule
            )
        }
    }
}