@file:JvmName("NavigationEntriesKt")

package com.alican.navigation3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.alican.navigation3.extension.addRouteSafely
import com.alican.navigation3.scenes.home.HomeScene
import com.alican.navigation3.scenes.movie.detail.MovieDetailScene
import com.alican.navigation3.scenes.movie.detail.MovieDetailViewModel
import com.alican.navigation3.scenes.movie.list.MovieListScreen
import com.alican.navigation3.scenes.movie.list.MovieListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun EntryProviderScope<NavKey>.MovieDetailEntry(
    backStack: NavBackStack<NavKey>
) {
    entry<EntryRoutes.MovieDetail> { entry ->
        val movieId = entry.movie
        val viewModel: MovieDetailViewModel = koinViewModel(
            parameters = { parametersOf(movieId) }
        )
        MovieDetailScene(
            viewModel = viewModel,
            onBack = {
                backStack.removeLastOrNull()
            }
        )
    }
}

@Composable
fun EntryProviderScope<NavKey>.MovieListEntry(
    backStack: NavBackStack<NavKey>
) {
    entry<EntryRoutes.MovieList> { entry ->
        val movieType = entry.movieType
        val viewModel: MovieListViewModel = koinViewModel(
            parameters = { parametersOf(movieType) }
        )

        MovieListScreen(
            onBack = {
                backStack.removeLastOrNull()
            },
            viewModel = viewModel,
            onMovieDetail = { movie ->
                val route = EntryRoutes.MovieDetail(movie = movie)
                backStack.addRouteSafely(route)
            }
        )
    }
}

@Composable
fun EntryProviderScope<NavKey>.HomeEntry(
    backStack: NavBackStack<NavKey>
) {
    entry<EntryRoutes.Home> {
        HomeScene(
            onMovieList = { movieType ->
                val route = EntryRoutes.MovieList(movieType)
                backStack.addRouteSafely(route)
            },
            onMovieDetail = { movieId ->
                val route = EntryRoutes.MovieDetail(movieId)
                backStack.addRouteSafely(route)
            }
        )
    }
}