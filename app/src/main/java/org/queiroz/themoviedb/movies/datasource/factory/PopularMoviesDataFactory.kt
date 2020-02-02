package org.queiroz.themoviedb.movies.datasource.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.movies.datasource.PopularMoviesDataSource
import org.queiroz.themoviedb.movies.repository.MoviesRepository
import java.util.concurrent.Executor

class PopularMoviesDataFactory(
    private val repository: MoviesRepository,
    private val coroutineScope: CoroutineScope,
    private val networkExecutor: Executor
) : DataSource.Factory<Int, TmdbMovies.Movie>() {

    private val _source = MutableLiveData<PopularMoviesDataSource>()
    val source: LiveData<PopularMoviesDataSource> = _source

    override fun create(): DataSource<Int, TmdbMovies.Movie> =
        PopularMoviesDataSource(repository, coroutineScope, networkExecutor).also {
            _source.postValue(it)
        }

}
