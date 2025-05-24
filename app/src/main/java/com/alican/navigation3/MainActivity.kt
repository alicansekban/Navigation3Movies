package com.alican.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alican.navigation3.navigation.AppNavDisplay
import com.alican.navigation3.navigation.Home
import com.alican.navigation3.ui.theme.Navigation3TutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            val backStack = remember { mutableStateListOf<Any>(Home) }

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