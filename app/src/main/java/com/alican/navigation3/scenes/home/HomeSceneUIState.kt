package com.alican.navigation3.scenes.home

import com.alican.navigation3.domain.ui_model.MovieUIModel


data class HomeSceneUIStateModel(
    val isLoading: Boolean = false,
    val popularMovies: List<MovieUIModel> = emptyList(),
    val posterMovie: MovieUIModel? = null
)