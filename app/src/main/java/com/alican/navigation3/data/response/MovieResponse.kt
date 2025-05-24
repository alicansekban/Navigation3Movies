package com.alican.navigation3.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("original_language")
    val original_language: String? = null,

    @SerialName("original_title")
    val original_title: String? = null,

    @SerialName("video")
    val video: Boolean? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("genre_ids")
    val genre_ids: List<Int>? = null,

    @SerialName("poster_path")
    val poster_path: String? = null,

    @SerialName("backdrop_path")
    val backdrop_path: String? = null,

    @SerialName("release_date")
    val release_date: String? = null,

    @SerialName("popularity")
    val popularity: Double? = null,

    @SerialName("vote_average")
    val vote_average: Double? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("adult")
    val adult: Boolean? = null,

    @SerialName("vote_count")
    val vote_count: Int? = null
)