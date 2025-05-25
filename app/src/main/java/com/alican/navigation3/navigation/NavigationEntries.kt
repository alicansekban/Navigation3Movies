package com.alican.navigation3.navigation

import com.alican.navigation3.domain.ui_model.MovieUIModel


data object Home


data class MovieList(val movieType: MovieType)


enum class MovieType {
    POPULAR,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING

}


data class MovieDetail(val movie: MovieUIModel)