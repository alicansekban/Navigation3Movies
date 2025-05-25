package com.alican.navigation3.scenes.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.navigation3.domain.interactor.MovieInteractor
import com.alican.navigation3.navigation.MovieType
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

class MovieListViewModel(
    private val movieType: MovieType,
    private val interactor: MovieInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListUIStateModel())
    val uiState: StateFlow<MovieListUIStateModel> = _uiState.onStart {
        getMovies(_uiState.value.currentPage)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), _uiState.value)

    fun onSceneEvents(event: MovieListUIEvents) {
        when (event) {
            MovieListUIEvents.GetNextPage -> {
                getMovies(_uiState.value.currentPage.plus(1))
            }
        }
    }

    private fun getMovies(page: Int) {
        when (movieType) {
            MovieType.POPULAR -> getPopularMovies(page = page)

            MovieType.TOP_RATED -> getTopRatedMovies(page = page)
            MovieType.UPCOMING -> getUpComingMovies(page = page)
            MovieType.NOW_PLAYING -> getNowPlayingMovies(page = page)
        }
    }

    private fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            interactor.getPopularMovies(page).collect { state ->
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = data
                        )
                    }
                }
                state.onError { message, originalError ->
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
                state.onLoading {
                    _uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    private fun getTopRatedMovies(page: Int) {
        viewModelScope.launch {
            interactor.getTopRatedMovies(page).collect { state ->
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = data
                        )
                    }
                }
                state.onError { message, originalError ->
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
                state.onLoading {
                    _uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    private fun getUpComingMovies(page: Int) {
        viewModelScope.launch {
            interactor.getUpComingMovies(page).collect { state ->
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = data
                        )
                    }
                }
                state.onError { message, originalError ->
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
                state.onLoading {
                    _uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    private fun getNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            interactor.getNowPlayingMovies(page).collect { state ->
                state.onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = data
                        )
                    }
                }
                state.onError { message, originalError ->
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
                state.onLoading {
                    _uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}