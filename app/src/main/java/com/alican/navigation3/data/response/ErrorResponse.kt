package com.alican.navigation3.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("errors")
    val errors: List<ErrorItemResponse>? = null,

    @SerialName("message")
    val message: String,

    @SerialName("code")
    val code: String
)

@Serializable
data class ErrorItemResponse(

    @SerialName("detail")
    val detail: String,

    @SerialName("status")
    val status: String
)