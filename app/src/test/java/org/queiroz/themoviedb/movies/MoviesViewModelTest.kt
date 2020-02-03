@file:Suppress("UNCHECKED_CAST")

package org.queiroz.themoviedb.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.reset
import org.mockito.Mockito.verify
import org.queiroz.themoviedb.FakeMoviesRepository
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.movies.repository.MoviesRepository
import org.queiroz.themoviedb.utils.CoroutinesTestRule
import org.queiroz.themoviedb.utils.mock

class MoviesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel
    private lateinit var repository: MoviesRepository
    private lateinit var observer: Observer<PagedList<TmdbMovies.Movie>>

    @Before
    fun setUp() {
        observer = mock()
        repository = FakeMoviesRepository()
        viewModel = MoviesViewModel(repository)
    }

    @After
    fun tearDown() {
        reset(observer)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun liveDataPopularMoviesEmitPagedListData() = runBlockingTest {
        val expectedResult = FakeMoviesRepository.getMockedPagedList()
        viewModel.popularMovies().observeForever(observer)
        observer.onChanged(expectedResult)
        verify(observer).onChanged(expectedResult)
    }

}
