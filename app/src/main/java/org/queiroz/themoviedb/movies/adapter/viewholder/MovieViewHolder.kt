package org.queiroz.themoviedb.movies.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.databinding.ItemMovieBinding
import org.queiroz.themoviedb.model.TmdbMovies

class MovieViewHolder(
    private val binding: ViewDataBinding,
    private val itemClickedEvent: (movie: TmdbMovies.Movie?, position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TmdbMovies.Movie?) = with(binding.root) {
        binding.setVariable(BR.movie, movie)
        binding.executePendingBindings()
        val posterPath = "https://image.tmdb.org/t/p/w342${movie?.posterPath}"
        Picasso.get().load(posterPath).placeholder(R.drawable.placeholder_image).into(poster)
        if (movie != null) setPopularityBgColor(movie)
        setOnClickListener { itemClickedEvent(movie, adapterPosition) }
    }

    private fun setPopularityBgColor(movie: TmdbMovies.Movie) = with(binding.root) {
        val voteAverage = movie.voteAverage * 10
        when {
            voteAverage >= 75 -> {
                score.setPercentageBackgroundColor(getColor(R.color.green))
            }
            voteAverage >= 50 -> {
                score.setPercentageBackgroundColor(getColor(R.color.blueLight))
            }
            voteAverage >= 25 -> {
                score.setPercentageBackgroundColor(getColor(R.color.orange))
            }
            voteAverage < 25 -> {
                score.setPercentageBackgroundColor(getColor(R.color.red))
            }
        }
    }

    private fun getColor(@ColorRes color: Int): Int =
        ResourcesCompat.getColor(binding.root.resources, color, null)

    companion object {

        fun create(
            parent: ViewGroup,
            itemClickedEvent: (movie: TmdbMovies.Movie?, position: Int) -> Unit
        ): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemMovieBinding =
                ItemMovieBinding.inflate(layoutInflater, parent, false)
            return MovieViewHolder(binding, itemClickedEvent)
        }

    }
}
