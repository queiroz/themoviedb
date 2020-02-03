package org.queiroz.themoviedb.movies.repository

import org.queiroz.themoviedb.api.ApiService
import org.queiroz.themoviedb.api.networkRequest
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val service: ApiService
): MoviesRepository {
    override suspend fun getPopularMovies(page: Int): Resource<TmdbMovies> =
        networkRequest { service.getPopularMovies(page) }
}
