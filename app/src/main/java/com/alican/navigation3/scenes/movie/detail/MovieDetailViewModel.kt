package com.alican.navigation3.scenes.movie.detail

import androidx.lifecycle.ViewModel
import com.alican.navigation3.domain.interactor.MovieInteractor

class MovieDetailViewModel(
    private val movieId: String,
    private val interactor: MovieInteractor
) : ViewModel() {
}