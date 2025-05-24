package com.alican.navigation3.navigation


data object Home


data class MovieList(val movieType: MovieType)

enum class MovieType {
    POPULAR,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING

}