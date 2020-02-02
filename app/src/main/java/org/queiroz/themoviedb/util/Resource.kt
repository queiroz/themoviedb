package org.queiroz.themoviedb.util

sealed class Resource<T> {
    data class Loading<T>(var loading: Boolean, var message: String?) : Resource<T>()
    data class Success<T>(var data: T?, var message: String?) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
    companion object {
        fun <T> loading(isLoading: Boolean, message: String?): Resource<T> =
            Loading(isLoading, message)
        fun <T> success(data: T?, message: String? = null): Resource<T> = Success(data, message)
        fun <T> error(message: String): Resource<T> = Error(message)
    }
}
