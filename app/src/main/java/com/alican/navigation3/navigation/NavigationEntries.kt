package com.alican.navigation3.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
data object Home : NavKey


@Serializable
data class MovieList(val movieType: MovieType) : NavKey

enum class MovieType {
    POPULAR,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING

}