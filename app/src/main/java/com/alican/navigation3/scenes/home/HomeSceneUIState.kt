package com.alican.navigation3.scenes.home

import com.alican.navigation3.domain.ui_model.MovieUIModel


data class HomeSceneUIStateModel(
    val isOverallLoading: Boolean = true,
    val popularMovies: List<MovieUIModel> = emptyList(),
    val posterMovie: MovieUIModel? = null,

    val nowPlayingMovies: List<MovieUIModel> = emptyList(),
    val isNowPlayingLoading: Boolean = true,

    val upComingMovies: List<MovieUIModel> = emptyList(),
    val isUpComingLoading: Boolean = true,

    val topRatedMovies: List<MovieUIModel> = emptyList(),
    val isTopRatedLoading: Boolean = true
)