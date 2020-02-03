package org.queiroz.themoviedb.movies.repository

import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.util.Resource

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): Resource<TmdbMovies>
}
