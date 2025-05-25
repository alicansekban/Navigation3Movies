package com.alican.navigation3.domain.mapper

import com.alican.navigation3.BuildConfig
import com.alican.navigation3.data.response.MovieResponse
import com.alican.navigation3.domain.ui_model.MovieUIModel

fun MovieResponse.toUIModel(): MovieUIModel {
    return MovieUIModel(
        id = id,
        name = title,
        description = overview,
        imageUrl = BuildConfig.BASE_POSTER_URL + poster_path,
        releaseDate = release_date,
        voteAverage = vote_average
    )
}