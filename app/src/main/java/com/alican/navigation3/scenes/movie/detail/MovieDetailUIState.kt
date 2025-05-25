package com.alican.navigation3.scenes.movie.detail

import com.alican.navigation3.domain.ui_model.MovieUIModel

data class MovieDetailUIStateModel(
    val isLoading: Boolean = false,
    val movieDetail: MovieUIModel? = null,
    val error: String? = null
)