@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.alican.navigation3.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay


@Composable
fun AppNavDisplay(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    entries: List<NavEntry<NavKey>>,
) {
    NavDisplay(
        entries = entries,
        modifier = modifier,
        onBack = { navigator.goBack() },
    )
}