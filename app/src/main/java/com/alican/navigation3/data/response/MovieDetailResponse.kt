package com.alican.navigation3.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(

    @SerialName("original_language")
    val original_language: String,

    @SerialName("imdb_id")
    val imdb_id: String,

    @SerialName("video")
    val video: Boolean,

    @SerialName("title")
    val title: String,

    @SerialName("backdrop_path")
    val backdrop_path: String,

    @SerialName("revenue")
    val revenue: Int,

    @SerialName("popularity")
    val popularity: Double,

    @SerialName("id")
    val id: Int,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("budget")
    val budget: Int,

    @SerialName("overview")
    val overview: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("runtime")
    val runtime: Int,

    @SerialName("poster_path")
    val poster_path: String,

    @SerialName("release_date")
    val release_date: String,

    @SerialName("vote_average")
    val vote_average: Double,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("homepage")
    val homepage: String,

    @SerialName("status")
    val status: String
)