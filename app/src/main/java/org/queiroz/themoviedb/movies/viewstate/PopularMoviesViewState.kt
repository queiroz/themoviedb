package org.queiroz.themoviedb.movies.viewstate

import org.queiroz.themoviedb.model.TmdbMovies

sealed class PopularMoviesViewState {

    data class DisplayError(val message: String) : PopularMoviesViewState()
    data class DisplayPopularMovies(val movies: List<TmdbMovies.Movie>) : PopularMoviesViewState()

    companion object {
        fun displayError(message: String) =
            DisplayError(
                message
            )

        fun displayPopularMovies(movies: List<TmdbMovies.Movie>) =
            DisplayPopularMovies(
                movies
            )
    }

}
