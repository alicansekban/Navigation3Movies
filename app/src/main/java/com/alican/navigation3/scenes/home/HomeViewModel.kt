package com.alican.navigation3.scenes.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.navigation3.domain.interactor.MovieInteractor
import com.alican.navigation3.domain.ui_model.MovieUIModel
import com.alican.navigation3.utils.onError
import com.alican.navigation3.utils.onLoading
import com.alican.navigation3.utils.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class HomeViewModel(
    private val interactor: MovieInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeSceneUIStateModel())
    val uiState = _uiState.asStateFlow()
    private var currentPosterIndex = 0

    init {
        getPopularMovies()
        getNowPlayingMovies()
        getUpComingMovies()
        getTopRatedMovies()
    }

    private fun getUpComingMovies() {
        viewModelScope.launch {
            interactor.getUpComingMovies(1).collect { state ->
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            upComingMovies = data
                        )
                    }
                }
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            interactor.getNowPlayingMovies(1).collect { state ->
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            nowPlayingMovies = data

                        )
                    }
                }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            interactor.getTopRatedMovies(1).collect { state ->
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            topRatedMovies = data
                        )
                    }
                }
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            interactor.getPopularMovies(1).collect { state ->
                state.onLoading {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                        )
                    }
                }
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            popularMovies = data
                        )
                    }
                    if (data.isNotEmpty()) {
                        _uiState.update {
                            it.copy(
                                posterMovie = data.first()
                            )
                        }
                        changePosterImage(availablePosters = data)
                    }
                }
                state.onError { message, originalError ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }

    private fun changePosterImage(availablePosters: List<MovieUIModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {
                delay(3000L)

                if (availablePosters.isEmpty()) continue
                currentPosterIndex = (currentPosterIndex + 1) % availablePosters.size

                _uiState.update {
                    it.copy(posterMovie = availablePosters[currentPosterIndex])
                }
            }
        }
    }

}