package com.alican.navigation3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.alican.navigation3.extension.addRouteSafely
import com.alican.navigation3.extension.removeRouteSafely
import com.alican.navigation3.scenes.home.HomeScene
import com.alican.navigation3.scenes.movie.list.MovieListScreen
import com.alican.navigation3.scenes.movie.list.MovieListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun AppNavDisplay(
    modifier: Modifier = Modifier,
    backStack: SnapshotStateList<Any>
) {

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { keysToRemove -> repeat(keysToRemove) { backStack.removeRouteSafely() } },
        entryProvider = entryProvider {
            entry<Home> {
                HomeScene(
                    onMovieList = { movieType ->
                        val route = MovieList(movieType)
                        backStack.addRouteSafely(route)
                    }
                )
            }

            entry<MovieList> { entry ->
                val movieType = entry.movieType
                val viewModel: MovieListViewModel = koinViewModel(
                    parameters = { parametersOf(movieType) }
                )
                MovieListScreen(
                    onBack = {
                        backStack.removeRouteSafely()
                    },
                    viewModel = viewModel
                )
            }
        }
    )
}