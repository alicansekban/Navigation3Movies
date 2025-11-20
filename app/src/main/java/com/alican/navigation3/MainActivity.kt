package com.alican.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alican.navigation3.navigation.AppBottomBar
import com.alican.navigation3.navigation.AppNavDisplay
import com.alican.navigation3.navigation.BottomNavRoutes
import com.alican.navigation3.navigation.Navigator
import com.alican.navigation3.navigation.appEntryProvider
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

            val entryProvider = appEntryProvider(navigator)

            val isBottomBarVisible by remember {
                mutableStateOf(
                    navigationState.backStacks.entries.last().key in bottomBarItems
                )
            }

            Navigation3TutorialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (isBottomBarVisible) {
                            AppBottomBar(
                                bottomBarItems = bottomBarItems,
                                navigationState = navigationState,
                                navigator = navigator
                            )
                        }

                    }
                ) { innerPadding ->
                    AppNavDisplay(
                        entries = navigationState.toEntries(entryProvider = entryProvider),
                        modifier = Modifier.padding(innerPadding),
                        navigator = navigator,
                    )
                }
            }
        }
    }
}
