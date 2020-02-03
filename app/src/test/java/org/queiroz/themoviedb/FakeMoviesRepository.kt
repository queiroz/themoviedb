package org.queiroz.themoviedb

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.movies.repository.MoviesRepository
import org.queiroz.themoviedb.util.Resource

class FakeMoviesRepository : MoviesRepository {

    private val movies = listOf(
        TmdbMovies.Movie(
            id = 419704,
            popularity = 455.208f,
            voteCount = 2166,
            video = false,
            posterPath = "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
            adult = false,
            backdropPath = "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
            originalLanguage = "en",
            originalTitle = "Ad Astra",
            genreIds = listOf(12, 18, 9648, 878, 53),
            title = "Ad Astra",
            voteAverage = 6f,
            overview = "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
            releaseDate = "2019-09-17"
        )
    )

    private val successResource = Resource.success(
        TmdbMovies(
            page = 1,
            totalResults = 1,
            totalPages = 1,
            results = movies
        )
    )

    override suspend fun getPopularMovies(page: Int): Resource<TmdbMovies> {
        return successResource
    }

    companion object {

        private val repository = FakeMoviesRepository()

        fun getMockedPagedList(): PagedList<TmdbMovies.Movie> {
            val pagedList = Mockito.mock(PagedList::class.java) as PagedList<TmdbMovies.Movie>
            Mockito.`when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
                val index = invocation.arguments.first() as Int
                repository.movies[index]
            }
            Mockito.`when`(pagedList.size).thenReturn(repository.movies.size)
            return pagedList
        }

    }
}
