package com.alican.navigation3.scenes.movie.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.navigation3.content.MovieItemContent
import com.alican.navigation3.content.ShimmerMovieItemPlaceholder
import com.alican.navigation3.content.rememberShimmerBrush
import com.alican.navigation3.domain.ui_model.MovieUIModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onBack: () -> Unit = {},
    onMovieDetail: (MovieUIModel) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyGridState()
    val brush = rememberShimmerBrush()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(
            items = uiState.movies,
            key = { it.id }
        ) { movie ->
            if (uiState.isLoading) {
                ShimmerMovieItemPlaceholder(brush = brush)
            } else {
                MovieItemContent(movie = movie, onMovieDetail = onMovieDetail)
            }
        }
    }
}