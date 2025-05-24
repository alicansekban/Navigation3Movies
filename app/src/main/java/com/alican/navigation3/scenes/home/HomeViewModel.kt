package com.alican.navigation3.scenes.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeSceneUIStateModel())
    val uiState = _uiState.asStateFlow()
}