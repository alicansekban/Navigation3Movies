package com.alican.navigation3.navigation

import androidx.navigation3.runtime.NavKey
import com.alican.navigation3.domain.ui_model.MovieUIModel
import kotlinx.serialization.Serializable

sealed class EntryRoutes : NavKey {
    @Serializable
    data object Home : EntryRoutes()

    @Serializable
    data class MovieList(val movieType: MovieType) : EntryRoutes()

    @Serializable
    data class MovieDetail(val movie: MovieUIModel) : EntryRoutes()
}


enum class MovieType {
    POPULAR,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING

}
