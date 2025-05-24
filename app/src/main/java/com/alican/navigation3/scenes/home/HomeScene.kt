package com.alican.navigation3.scenes.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.keepScreenOn
import androidx.compose.ui.text.style.TextAlign
import com.alican.navigation3.navigation.MovieType
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScene(
    viewModel: HomeViewModel = koinViewModel(),
    onMovieList: (MovieType) -> Unit = {}
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "This is Home Scene",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .keepScreenOn()
                .clickable {
                    onMovieList.invoke(MovieType.POPULAR)
                }
        )
    }
}