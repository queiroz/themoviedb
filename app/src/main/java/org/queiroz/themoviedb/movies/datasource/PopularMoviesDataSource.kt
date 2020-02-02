package org.queiroz.themoviedb.movies.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.movies.repository.MoviesRepository
import org.queiroz.themoviedb.movies.viewstate.NetworkState
import org.queiroz.themoviedb.util.Resource
import java.util.concurrent.Executor

class PopularMoviesDataSource(
    private val repository: MoviesRepository,
    private val coroutineScope: CoroutineScope,
    private val networkExecutor: Executor
) : PageKeyedDataSource<Int, TmdbMovies.Movie>() {

    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.run {
            networkExecutor.execute {
                invoke()
            }
        }
    }

    override fun loadBefore(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, TmdbMovies.Movie>
    ) {
    }

    override fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, TmdbMovies.Movie>
    ) {
        networkState.postValue(NetworkState.LOADING)

        coroutineScope.launch(Dispatchers.IO) {
            val page = 1
            when (val response = repository.getPopularMovies(page)) {
                is Resource.Success -> {
                    val movies = response.data?.results
                    if (movies != null) {
                        retry = null
                        callback.onResult(movies, null, page + 1)
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        error { loadInitial(params, callback) }
                    }
                }
                is Resource.Error -> {
                    error(response.message) { loadInitial(params, callback) }
                }
            }
        }
    }

    override fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, TmdbMovies.Movie>
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            val page = params.key
            when (val response = repository.getPopularMovies(page)) {
                is Resource.Success -> {
                    val movies = response.data?.results
                    if (movies != null) {
                        callback.onResult(movies, page + 1)
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        error { loadAfter(params, callback) }
                    }
                }
                is Resource.Error -> {
                    error(response.message) { loadAfter(params, callback) }
                }
            }
        }
    }

    private fun error(message: String = "Something went wrong!", block: () -> Unit) {
        retry = block
        networkState.postValue(NetworkState.error(message))
    }
}
