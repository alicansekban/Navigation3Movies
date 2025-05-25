package com.alican.navigation3.utils


sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val error: ErrorModel) :
        NetworkResult<Nothing>()
}

data class ErrorModel(
    val errorCode: String? = null,
    val errorMessage: String = "",
    val errorType: ErrorType = ErrorType.SYSTEM,
    val statusCode: StatusCode = StatusCode.INTERNAL_SERVER_ERROR,
)
enum class ErrorType {
    BUSINESS, VALIDATION, SYSTEM, NETWORK
}

enum class StatusCode {
    NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR, UNAUTHORIZED, FORBIDDEN, TOO_MANY_REQUESTS, VALIDATION
}
