package org.queiroz.themoviedb.movies.viewstate

enum class Status {
    LOADING,
    LOADED,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null
) {
    companion object {
        val LOADED = NetworkState(Status.LOADED)
        val LOADING = NetworkState(Status.LOADING)
        val FAILED = NetworkState(Status.FAILED)

        fun error(msg: String?): NetworkState {
            val message = when {
                msg == null -> "Unknown Error"
                msg.contains("Unable to resolve host") -> "No network connectivity"
                msg.contains("timeout") -> "Could not connect to the server"
                else -> msg
            }
            return NetworkState(Status.FAILED, message)
        }
    }
}
