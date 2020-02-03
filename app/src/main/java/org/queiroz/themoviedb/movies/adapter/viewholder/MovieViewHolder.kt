package org.queiroz.themoviedb.movies.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.config.TheMovieDataBaseConfig
import org.queiroz.themoviedb.databinding.ItemMovieBinding
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.util.debounceClick
import org.queiroz.themoviedb.util.setPopularityBgColor

class MovieViewHolder(
    private val binding: ViewDataBinding,
    private val itemClickedEvent: (movie: TmdbMovies.Movie?, posterImageView: View, position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TmdbMovies.Movie?) = with(binding.root) {
        binding.setVariable(BR.movie, movie)
        binding.executePendingBindings()
        movie?.let {
            val posterPath = TheMovieDataBaseConfig.getPosterUrl(movie.posterPath)
            Picasso.get().load(posterPath).placeholder(R.drawable.placeholder_image).into(poster)
            score.setPopularityBgColor(movie.voteAverage)
        }
        debounceClick {
            itemClickedEvent(movie, poster, adapterPosition)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            itemClickedEvent: (movie: TmdbMovies.Movie?, posterImageView: View, position: Int) -> Unit
        ): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemMovieBinding =
                ItemMovieBinding.inflate(layoutInflater, parent, false)
            return MovieViewHolder(binding, itemClickedEvent)
        }

    }
}
