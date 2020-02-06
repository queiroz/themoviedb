package org.queiroz.themoviedb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.movies.MoviesViewModel
import org.queiroz.themoviedb.movies.adapter.PopularMoviesAdapter
import org.queiroz.themoviedb.util.progressBar
import org.queiroz.themoviedb.util.waitForTransition
import javax.inject.Inject

class PopularMoviesFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }
    private val popularMoviesAdapter by lazy {
        PopularMoviesAdapter({
            viewModel.retry()
        }) { movie, posterImageView, position ->
            if (movie != null) {
                posterImageView.transitionName = "poster-$position"
                val action = PopularMoviesFragmentDirections
                    .actionPopularMoviesFragmentToMovieDetailFragment(
                        movie = movie,
                        transitionName = posterImageView.transitionName
                    )
                val extras = FragmentNavigatorExtras(
                    posterImageView to posterImageView.transitionName
                )
                findNavController().navigate(action, extras)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_popular_movies, container, false)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupObservers()
        waitForTransition(popular_movies)
    }

    private fun setupAdapter() = with(popular_movies) {
        adapter = popularMoviesAdapter
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
    }

    private fun setupObservers() {
        viewModel.popularMovies().observe(viewLifecycleOwner, Observer { pagedList ->
            popularMoviesAdapter.submitList(pagedList)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer { networkState ->
            popularMoviesAdapter.setNetworkState(networkState)
            progressBar(networkState)
        })
    }

}
