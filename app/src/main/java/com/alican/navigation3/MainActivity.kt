package com.alican.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberNavBackStack
import com.alican.navigation3.navigation.AppNavDisplay
import com.alican.navigation3.navigation.EntryRoutes
import com.alican.navigation3.ui.theme.Navigation3TutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            val backStack = rememberNavBackStack(EntryRoutes.Home)

            Navigation3TutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = backStack
                    )
                }
            }
        }
    }
}