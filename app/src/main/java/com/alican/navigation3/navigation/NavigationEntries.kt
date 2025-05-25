package com.alican.navigation3.navigation

import androidx.navigation3.runtime.NavKey
import com.alican.navigation3.domain.ui_model.MovieUIModel
import kotlinx.serialization.Serializable

sealed class Scenes : NavKey {
    @Serializable
    data object Home : Scenes()

    @Serializable
    data class MovieList(val movieType: MovieType) : Scenes()

    @Serializable
    data class MovieDetail(val movie: MovieUIModel) : Scenes()
}

enum class MovieType {
    POPULAR,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING

}
