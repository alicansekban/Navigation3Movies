package com.alican.navigation3.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.alican.navigation3.domain.ui_model.MovieUIModel
import kotlinx.serialization.Serializable

sealed class EntryRoutes : NavKey {
    @Serializable
    data object Home : EntryRoutes()

    @Serializable
    data class MovieList(val movieType: MovieType) : EntryRoutes()

    @Serializable
    data class MovieDetail(val movie: MovieUIModel) : EntryRoutes()
}
sealed interface BottomNavRoutes : NavKey {
    val title: String
    val icon: ImageVector

    @Serializable
    data object Home : BottomNavRoutes {
        override val title: String = "Home"
        override val icon: ImageVector = Icons.Default.Home
    }

    @Serializable
    data object Favorites : BottomNavRoutes {
        override val title: String = "Favorites"
        override val icon: ImageVector = Icons.Default.Favorite
    }

    @Serializable
    data object Profile : BottomNavRoutes {
        override val title: String = "Profile"
        override val icon: ImageVector = Icons.Default.MoreVert
    }
}


enum class MovieType {
    POPULAR,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING

}
