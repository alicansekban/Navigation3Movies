package com.alican.navigation3.scenes.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.navigation3.domain.interactor.MovieInteractor
import com.alican.navigation3.utils.onError
import com.alican.navigation3.utils.onLoading
import com.alican.navigation3.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val interactor: MovieInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeSceneUIStateModel())
    val uiState = _uiState.asStateFlow()

    init {
        getPopularMovies()
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
}