package com.alican.navigation3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider

@Composable
fun appEntryProvider(navigator: Navigator): (NavKey) -> NavEntry<NavKey> {
    val entryProvider = entryProvider {
        HomeEntry(navigator)

        FavoritesEntry(navigator)

        ProfileEntry(navigator)
    }
    return entryProvider
}