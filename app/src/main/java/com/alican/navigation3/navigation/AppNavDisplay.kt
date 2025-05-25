@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.alican.navigation3.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.alican.navigation3.extension.addRouteSafely
import com.alican.navigation3.scenes.home.HomeScene
import com.alican.navigation3.scenes.movie.detail.MovieDetailScene
import com.alican.navigation3.scenes.movie.detail.MovieDetailViewModel
import com.alican.navigation3.scenes.movie.list.MovieListScreen
import com.alican.navigation3.scenes.movie.list.MovieListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun AppNavDisplay(
    modifier: Modifier = Modifier,
    backStack: NavBackStack
) {
    val listDetailStrategy = rememberListDetailSceneStrategy<Any>()
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        sceneStrategy = listDetailStrategy,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Scenes.Home>(
                metadata = ListDetailSceneStrategy.extraPane()
            ) {
                HomeScene(
                    onMovieList = { movieType ->
                        val route = Scenes.MovieList(movieType)
                        backStack.addRouteSafely(route)
                    },
                    onMovieDetail = { movieId ->
                        val route = Scenes.MovieDetail(movieId)
                        backStack.addRouteSafely(route)
                    }
                )
            }

            entry<Scenes.MovieList>(
                metadata = ListDetailSceneStrategy.listPane()
            ) { entry ->
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
                        val route = Scenes.MovieDetail(movie = movie)
                        backStack.addRouteSafely(route)
                    }
                )
            }

            entry<Scenes.MovieDetail>(
                metadata = ListDetailSceneStrategy.detailPane()
            ) { entry ->
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
    )
}