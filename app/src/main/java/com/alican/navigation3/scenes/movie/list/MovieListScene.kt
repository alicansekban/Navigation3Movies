package com.alican.navigation3.scenes.movie.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.alican.navigation3.navigation.MovieType
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = koinViewModel(),
    onBack: () -> Unit = {},
    movieType: MovieType
) {

    val context = LocalContext.current
    LaunchedEffect(movieType) {
        Toast.makeText(context, movieType.name, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = movieType.name,
            modifier = Modifier.clickable {
                onBack.invoke()
            }
        )
    }
}