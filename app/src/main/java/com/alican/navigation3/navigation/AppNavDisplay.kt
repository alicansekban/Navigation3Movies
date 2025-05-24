package com.alican.navigation3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.alican.navigation3.extension.addRouteSafely
import com.alican.navigation3.scenes.home.HomeScene
import com.alican.navigation3.scenes.movie.list.MovieListScreen


@Composable
fun AppNavDisplay(
    modifier: Modifier = Modifier,
    backStack: NavBackStack
) {

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { keysToRemove -> repeat(keysToRemove) { backStack.removeLastOrNull() } },
        entryProvider = entryProvider {
            entry<Home> {
                HomeScene(
                    onBack = {
                        backStack.removeFirstOrNull()
                    },
                    onMovieList = { movieType ->
                        val route = MovieList(movieType)
                        backStack.addRouteSafely(route)
                    }
                )
            }

            entry<MovieList> { entry ->
                val movieType = entry.movieType
                MovieListScreen(
                    onBack = {
                        backStack.remove(entry)
                    },
                    movieType = movieType
                )
            }
        }
    )
}