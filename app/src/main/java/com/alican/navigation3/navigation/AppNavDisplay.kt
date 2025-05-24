package com.alican.navigation3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.alican.navigation3.scenes.home.HomeScene


@Composable
fun AppNavDisplay(
    modifier: Modifier = Modifier,
    backStack: NavBackStack
) {

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeFirstOrNull() },
        entryProvider = entryProvider {
            entry<Home> {
                HomeScene()
            }
        }
    )
}