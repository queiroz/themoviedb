package org.queiroz.themoviedb.model

data class TmdbMovies(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<Movie>
) {
    data class Movie(
        val id: Int,
        val popularity: Float,
        val voteCount: Int,
        val video: Boolean,
        val posterPath: String,
        val adult: Boolean,
        val backdropPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int>,
        val title: String,
        val voteAverage: Float,
        val overview: String,
        val releaseDate: String
    )
}
