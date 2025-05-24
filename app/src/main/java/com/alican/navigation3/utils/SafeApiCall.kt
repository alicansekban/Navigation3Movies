package com.alican.navigation3.utils

import com.alican.navigation3.data.response.ErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json

suspend inline fun <reified T> safeApiCall(
    client: HttpClient,
    requestBuilder: HttpRequestBuilder.() -> Unit,
): NetworkResult<T> {
    return try {
        val response: HttpResponse = client.request {
            requestBuilder()
        }
        if (response.status.isSuccess()) {

            val responseData: T = response.body()
            NetworkResult.Success(responseData)

        } else {
            val errorBody = response.bodyAsText()
            val errorModel = parseError(errorBody = errorBody, status = response.status.value)
            NetworkResult.Error(error = errorModel)
        }
    } catch (t: Throwable) {
        when (t) {
            is ConnectTimeoutException,
            is HttpRequestTimeoutException -> NetworkResult.Error(
                error = ErrorModel(
                    errorMessage = "Request has timed out",
                    errorType = ErrorType.NETWORK,
                    statusCode = StatusCode.INTERNAL_SERVER_ERROR
                )
            )

            else -> NetworkResult.Error(
                error = ErrorModel(
                    errorMessage = t.message ?: t.localizedMessage,
                    errorType = ErrorType.SYSTEM,
                    statusCode = StatusCode.INTERNAL_SERVER_ERROR
                )
            )
        }
    }
}


fun parseError(errorBody: String, status: Int): ErrorModel {
    return try {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val errorResponse = json.decodeFromString<ErrorResponse>(errorBody)


        val statusCode = when (status) {
            400 -> StatusCode.BAD_REQUEST
            422 -> StatusCode.VALIDATION
            403 -> StatusCode.FORBIDDEN
            401 -> StatusCode.UNAUTHORIZED
            404 -> StatusCode.NOT_FOUND
            429 -> StatusCode.TOO_MANY_REQUESTS
            in 500..599 -> StatusCode.INTERNAL_SERVER_ERROR
            else -> StatusCode.INTERNAL_SERVER_ERROR
        }
        ErrorModel(
            errorCode = errorResponse.code,
            errorMessage = errorResponse.message,
            statusCode = statusCode,
        )
    } catch (e: Throwable) {
        ErrorModel(
            errorMessage = e.message ?: e.localizedMessage,
            errorType = ErrorType.SYSTEM,
            statusCode = StatusCode.INTERNAL_SERVER_ERROR,
            errorCode = status.toString()
        )

    }
}


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

data class ErrorDetailsModel(
    val fieldName: String? = null,
    val operatorCode: String? = null,
    val message: String? = null
)

enum class ErrorType {
    BUSINESS, VALIDATION, SYSTEM, NETWORK
}

enum class StatusCode {
    NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR, UNAUTHORIZED, FORBIDDEN, TOO_MANY_REQUESTS, VALIDATION
}
