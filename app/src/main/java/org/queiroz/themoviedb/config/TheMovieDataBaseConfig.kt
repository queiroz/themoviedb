package org.queiroz.themoviedb.config

object TheMovieDataBaseConfig {
    const val imageBaseUrl = "https://image.tmdb.org/t/p/"
    fun getPosterUrl(path: String) = "${imageBaseUrl}w342${path}"
    fun getBackdropUrl(path: String) = "${imageBaseUrl}original${path}"
}
