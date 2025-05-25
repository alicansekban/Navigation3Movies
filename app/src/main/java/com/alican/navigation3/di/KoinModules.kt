package com.alican.navigation3.di

import com.alican.navigation3.BuildConfig
import com.alican.navigation3.data.service.MovieService
import com.alican.navigation3.data.service.MovieServiceImpl
import com.alican.navigation3.domain.interactor.MovieInteractor
import com.alican.navigation3.domain.interactor.MovieInteractorImpl
import com.alican.navigation3.scenes.home.HomeViewModel
import com.alican.navigation3.scenes.movie.detail.MovieDetailViewModel
import com.alican.navigation3.scenes.movie.list.MovieListViewModel
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
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModules = module {
    // viewmodels
    viewModelOf(::HomeViewModel)
    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieDetailViewModel)

    // interactors

    singleOf(::MovieInteractorImpl) bind MovieInteractor::class


    // services
    singleOf(::MovieServiceImpl) bind MovieService::class
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