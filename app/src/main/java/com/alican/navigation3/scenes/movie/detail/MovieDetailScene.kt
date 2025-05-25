package com.alican.navigation3.scenes.movie.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.alican.navigation3.domain.ui_model.MovieUIModel

@Composable
fun MovieDetailScene(
    viewModel: MovieDetailViewModel,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        uiState.movieDetail?.let { movieUIModel ->
            MovieDetailSceneContent(movie = movieUIModel)
        }
    }
}

@Composable
private fun MovieDetailSceneContent(movie: MovieUIModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            model = movie.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
        )
        Text(
            text = movie.name,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = movie.description,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}