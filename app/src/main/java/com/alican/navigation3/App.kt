package com.alican.navigation3

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules()
            androidContext(androidContext = applicationContext)
        }
    }
}