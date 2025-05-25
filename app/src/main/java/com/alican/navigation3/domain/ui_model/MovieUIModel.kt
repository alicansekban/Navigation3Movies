package com.alican.navigation3.domain.ui_model

import kotlinx.serialization.Serializable

@Serializable
data class MovieUIModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
)
