package org.queiroz.themoviedb.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import org.queiroz.themoviedb.movies.datasource.factory.PopularMoviesDataFactory
import org.queiroz.themoviedb.movies.repository.MoviesRepository
import org.queiroz.themoviedb.movies.viewstate.NetworkState
import java.util.concurrent.Executor
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    repository: MoviesRepository
) : ViewModel() {

    companion object {
        const val PER_PAGE = 20
    }

    private val networkExecutor = Executor { command -> command.run() }

    private val popularMoviesDataFactory =
        PopularMoviesDataFactory(repository, viewModelScope, networkExecutor)

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(PER_PAGE)
        .setPageSize(PER_PAGE * 2)
        .build()

    val popularMovies = LivePagedListBuilder(popularMoviesDataFactory, pagedListConfig)
        .setFetchExecutor(networkExecutor)
        .build()

    var networkState: LiveData<NetworkState> =
        Transformations.switchMap(popularMoviesDataFactory.source) { dataSource ->
            dataSource.networkState
        }

    fun retry() = popularMoviesDataFactory.source.value?.retryAllFailed()

}
