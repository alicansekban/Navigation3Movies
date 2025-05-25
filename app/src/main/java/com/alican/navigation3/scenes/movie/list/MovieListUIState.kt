package com.alican.navigation3.scenes.movie.list

import com.alican.navigation3.domain.ui_model.MovieUIModel

sealed class MovieListUIEvents {
    data object GetNextPage : MovieListUIEvents()
}

data class MovieListUIStateModel(
    val isLoading: Boolean = false,
    val movies: List<MovieUIModel> = emptyList(),
    val hasNextPage: Boolean = false,
    val currentPage: Int = 1

)