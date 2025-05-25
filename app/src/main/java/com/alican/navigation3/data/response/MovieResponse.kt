package com.alican.navigation3.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(

    @SerialName("overview")
    val overview: String,

    @SerialName("original_language")
    val original_language: String,

    @SerialName("original_title")
    val original_title: String,

    @SerialName("video")
    val video: Boolean,

    @SerialName("title")
    val title: String,

    @SerialName("genre_ids")
    val genre_ids: List<Int>,

    @SerialName("poster_path")
    val poster_path: String,

    @SerialName("backdrop_path")
    val backdrop_path: String,

    @SerialName("release_date")
    val release_date: String,

    @SerialName("popularity")
    val popularity: Double,

    @SerialName("vote_average")
    val vote_average: Double,

    @SerialName("id")
    val id: Int,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("vote_count")
    val vote_count: Int
)