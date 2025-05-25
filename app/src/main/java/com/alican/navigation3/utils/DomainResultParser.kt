package com.alican.navigation3.utils


sealed class DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error(val message: String, val originalErrorModel: ErrorModel? = null) :
        DomainResult<Nothing>()

    data object Loading : DomainResult<Nothing>()
}

inline fun <T> DomainResult<T>.onSuccess(action: (data: T) -> Unit): DomainResult<T> {
    if (this is DomainResult.Success<T>) {
        action(this.data)
    }
    return this
}

inline fun <T> DomainResult<T>.onError(action: (message: String, originalError: ErrorModel?) -> Unit): DomainResult<T> {
    if (this is DomainResult.Error) {
        action(this.message, this.originalErrorModel)
    }
    return this
}

inline fun <T> DomainResult<T>.onLoading(action: () -> Unit): DomainResult<T> {
    if (this is DomainResult.Loading) {
        action()
    }
    return this
}