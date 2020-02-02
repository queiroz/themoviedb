package org.queiroz.themoviedb.api

import org.queiroz.themoviedb.model.TmdbMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): TmdbMovies

}
