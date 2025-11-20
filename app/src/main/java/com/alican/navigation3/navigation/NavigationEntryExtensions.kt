@file:JvmName("NavigationEntriesKt")

package com.alican.navigation3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.alican.navigation3.scenes.home.HomeScene
import com.alican.navigation3.scenes.movie.detail.MovieDetailScene
import com.alican.navigation3.scenes.movie.detail.MovieDetailViewModel
import com.alican.navigation3.scenes.movie.list.MovieListScreen
import com.alican.navigation3.scenes.movie.list.MovieListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun EntryProviderScope<NavKey>.ProfileEntry(
    navigator: Navigator
) {
    entry<BottomNavRoutes.Profile> {
    }
}

@Composable
fun EntryProviderScope<NavKey>.FavoritesEntry(
    navigator: Navigator,
) {
    entry<BottomNavRoutes.Favorites> {

    }
}

@Composable
fun EntryProviderScope<NavKey>.HomeEntry(
    navigator: Navigator
) {
    entry<BottomNavRoutes.Home> {
        HomeScene(
            onMovieList = { movieType ->
                val route = EntryRoutes.MovieList(movieType)
                navigator.navigate(route)
            },
            onMovieDetail = { movieId ->
                val route = EntryRoutes.MovieDetail(movieId)
                navigator.navigate(route)
            }
        )
    }
    entry<EntryRoutes.MovieList> { entry ->
        val movieType = entry.movieType
        val viewModel: MovieListViewModel = koinViewModel(
            parameters = { parametersOf(movieType) }
        )

        MovieListScreen(
            onBack = {
                navigator.goBack()
            },
            viewModel = viewModel,
            onMovieDetail = { movie ->
                val route = EntryRoutes.MovieDetail(movie = movie)
                navigator.navigate(route)
            }
        )
    }
    entry<EntryRoutes.MovieDetail> { entry ->
        val movieId = entry.movie
        val viewModel: MovieDetailViewModel = koinViewModel(
            parameters = { parametersOf(movieId) }
        )
        MovieDetailScene(
            viewModel = viewModel,
            onBack = {
                navigator.goBack()
            }
        )
    }
}