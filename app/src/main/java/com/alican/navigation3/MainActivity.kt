package com.alican.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.alican.navigation3.navigation.AppBottomBar
import com.alican.navigation3.navigation.BottomNavRoutes
import com.alican.navigation3.navigation.FavoritesEntry
import com.alican.navigation3.navigation.HomeEntry
import com.alican.navigation3.navigation.Navigator
import com.alican.navigation3.navigation.ProfileEntry
import com.alican.navigation3.navigation.rememberNavigationState
import com.alican.navigation3.navigation.toEntries
import com.alican.navigation3.ui.theme.Navigation3TutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val bottomBarItems = listOf(
                BottomNavRoutes.Home,
                BottomNavRoutes.Favorites,
                BottomNavRoutes.Profile
            )

            val navigationState = rememberNavigationState(
                startRoute = BottomNavRoutes.Home,
                topLevelRoutes = bottomBarItems.toSet()
            )
            val navigator = remember { Navigator(navigationState) }

            val entryProvider = entryProvider {
                HomeEntry(navigator)

                FavoritesEntry(navigator)

                ProfileEntry(navigator)
            }

            Navigation3TutorialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AppBottomBar(
                            bottomBarItems = bottomBarItems,
                            navigationState = navigationState,
                            navigator = navigator
                        )

                    }
                ) { innerPadding ->
                    NavDisplay(
                        entries = navigationState.toEntries(entryProvider = entryProvider),
                        modifier = Modifier.padding(innerPadding),
                        onBack = { navigator.goBack() },
                    )
                }
            }
        }
    }
}