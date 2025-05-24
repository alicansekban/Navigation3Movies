package com.alican.navigation3.di

import com.alican.navigation3.BuildConfig
import com.alican.navigation3.scenes.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinModules = module {
    viewModelOf(::HomeViewModel)
}

val networkModule = module {
    single {
        HttpClient {
            defaultRequest {
                url(BuildConfig.BASE_URL)
                header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )

            }
        }
    }
}