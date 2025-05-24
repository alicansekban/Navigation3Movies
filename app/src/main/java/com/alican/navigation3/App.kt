package com.alican.navigation3

import android.app.Application
import com.alican.navigation3.di.koinModules
import com.alican.navigation3.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(koinModules, networkModule)
            androidContext(androidContext = applicationContext)
        }
    }
}