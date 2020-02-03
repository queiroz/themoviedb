package org.queiroz.themoviedb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.config.TheMovieDataBaseConfig
import org.queiroz.themoviedb.databinding.FragmentMovieDetailBinding
import org.queiroz.themoviedb.model.PageData
import org.queiroz.themoviedb.movies.adapter.MovieInfoAdapter
import org.queiroz.themoviedb.util.setPopularityBgColor
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MovieDetailFragment : Fragment() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val movieInfoAdapter by lazy { MovieInfoAdapter() }
    private val pages by lazy {
        listOf(
            PageData("Overview", MovieInfoAdapter.PAGE_OVERVIEW_LAYOUT, args.movie),
            PageData("Cast", MovieInfoAdapter.PAGE_CAST_LAYOUT, "Casting crew")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(
            inflater,
            R.layout.fragment_movie_detail,
            container,
            false
        )
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move)
        binding.movie = args.movie
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager()
        setupUI()
    }

    private fun setupViewPager() {
        movie_info_pages.apply {
            adapter = movieInfoAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        movieInfoAdapter.submitList(pages)
        setupTabs()
    }

    private fun setupTabs() {
        TabLayoutMediator(movie_info_tabs, movie_info_pages) { tab, position ->
            tab.text = pages[position].title
        }.attach()
        movie_info_tabs.getTabAt(0)?.select()
    }

    private fun setupUI() {
        setupBackdrop()
        setupPoster()
        setupScore()
    }

    private fun setupBackdrop() {
        args.movie?.backdropPath?.let {
            Picasso.get()
                .load(TheMovieDataBaseConfig.getBackdropUrl(it))
                .placeholder(R.drawable.placeholder_image)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(backdrop, object: Callback {
                    override fun onSuccess() {
                        startPostponedEnterTransition()
                    }
                    override fun onError(e: Exception?) {
                        Timber.d(e)
                    }
                })
            // Make sure we don't wait longer than a second for the image request
            postponeEnterTransition(1, TimeUnit.SECONDS)
        }
    }

    private fun setupPoster() {
        poster.transitionName = args.transitionName
        args.movie?.posterPath?.let {
            Picasso.get()
                .load(TheMovieDataBaseConfig.getPosterUrl(it))
                .placeholder(R.drawable.placeholder_image)
                .fit()
                .centerCrop()
                .into(poster)
        }
    }

    private fun setupScore() {
        args.movie?.voteAverage?.let { score.setPopularityBgColor(it) }
    }

}
