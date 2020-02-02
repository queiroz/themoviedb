package org.queiroz.themoviedb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.movies.MoviesViewModel
import org.queiroz.themoviedb.movies.adapter.PopularMoviesAdapter
import org.queiroz.themoviedb.util.progressBar
import org.queiroz.themoviedb.util.viewModelProvider
import timber.log.Timber
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MoviesViewModel
    private val popularMoviesAdapter by lazy {
        PopularMoviesAdapter({
            viewModel.retry()
        }) { movie, _ ->
            Timber.i("Movie: ${movie?.title}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() = with(popular_movies) {
        adapter = popularMoviesAdapter
        layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { pagedList ->
            popularMoviesAdapter.submitList(pagedList)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer { networkState ->
            popularMoviesAdapter.setNetworkState(networkState)
            progressBar(networkState)
        })
    }

}
