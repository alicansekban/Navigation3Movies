package com.alican.navigation3.scenes.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.alican.navigation3.navigation.MovieType
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScene(
    viewModel: HomeViewModel = koinViewModel(),
    onMovieList: (MovieType) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.popularMovies.isNotEmpty()) {
            SubcomposeAsyncImage(
                model = uiState.popularMovies[0].imageUrl,
                contentDescription = null,
                loading = {
                    CircularProgressIndicator()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.8f),
                contentScale = ContentScale.Fit
            )
        }
    }
}