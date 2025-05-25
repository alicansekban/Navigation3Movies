package com.alican.navigation3.scenes.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.navigation3.domain.interactor.MovieInteractor
import com.alican.navigation3.domain.ui_model.MovieUIModel
import com.alican.navigation3.utils.ErrorModel
import com.alican.navigation3.utils.onError
import com.alican.navigation3.utils.onLoading
import com.alican.navigation3.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movie: MovieUIModel,
    private val interactor: MovieInteractor
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailUIStateModel())
    val uiState: StateFlow<MovieDetailUIStateModel> = _uiState.onStart {
        _uiState.update {
            it.copy(
                movieDetail = movie
            )
        }
    }.stateIn(
        viewModelScope, SharingStarted.Eagerly, _uiState.value
    )

    private fun getMovieDetail() {
        viewModelScope.launch {
            interactor.getMovieDetail(movieId = movie.id).collect { state ->
                state.onLoading {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true
                    )
                }
                state.onSuccess { data: MovieUIModel ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movieDetail = data
                    )
                }
                state.onError { error: String, originalError: ErrorModel? ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error
                    )

                }
            }
        }
    }
}