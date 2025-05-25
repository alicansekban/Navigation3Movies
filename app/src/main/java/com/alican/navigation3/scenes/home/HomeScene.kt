package com.alican.navigation3.scenes.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.alican.navigation3.content.MovieItemContent
import com.alican.navigation3.content.ShimmerHomeMovieWidgetPlaceholder
import com.alican.navigation3.content.ShimmerMovieItemPlaceholder
import com.alican.navigation3.content.ShimmerPosterImagePlaceholder
import com.alican.navigation3.content.rememberShimmerBrush
import com.alican.navigation3.domain.ui_model.MovieUIModel
import com.alican.navigation3.navigation.MovieType
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScene(
    viewModel: HomeViewModel = koinViewModel(),
    onMovieList: (MovieType) -> Unit = {},
    onMovieDetail: (MovieUIModel) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    val brush = rememberShimmerBrush()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Crossfade(
            targetState = uiState.posterMovie,
            modifier = Modifier,
            label = "poster image",
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 0
            )
        ) { movie ->
            movie?.let { posterMovie ->
                SubcomposeAsyncImage(
                    model = posterMovie.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.9f)
                        .clickable {
                            onMovieDetail(posterMovie)
                        },
                    contentScale = ContentScale.FillBounds
                )
            } ?: run {
                ShimmerPosterImagePlaceholder(brush = brush)
            }

        }

        HomeMovieWidget(
            title = "UpComing Movies",
            onMovieList = { onMovieList(MovieType.UPCOMING) },
            onMovieDetail = onMovieDetail,
            movies = uiState.upComingMovies,
            showShimmer = uiState.isUpComingLoading
        )
        HomeMovieWidget(
            title = "Now Playing Movies",
            onMovieList = { onMovieList(MovieType.NOW_PLAYING) },
            onMovieDetail = onMovieDetail,
            movies = uiState.nowPlayingMovies,
            showShimmer = uiState.isNowPlayingLoading
        )
        HomeMovieWidget(
            title = "Top Rated Movies",
            onMovieList = { onMovieList(MovieType.TOP_RATED) },
            onMovieDetail = onMovieDetail,
            movies = uiState.topRatedMovies,
            showShimmer = uiState.isTopRatedLoading

        )

    }
}

@Composable
private fun HomeMovieWidget(
    title: String,
    showShimmer: Boolean,
    onMovieList: () -> Unit = {},
    onMovieDetail: (MovieUIModel) -> Unit = {},
    movies: List<MovieUIModel>
) {
    if (showShimmer) {
        ShimmerHomeMovieWidgetPlaceholder(
            itemCount = 5,
            itemContent = { brush ->
                ShimmerMovieItemPlaceholder(brush = brush)
            }
        )
    } else {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Show All",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.clickable {
                    onMovieList.invoke()
                }
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = movies,
                key = { it.id }
            ) { movie ->
                MovieItemContent(
                    movie = movie,
                    onMovieDetail = onMovieDetail
                )
            }
        }
    }
    }
}
