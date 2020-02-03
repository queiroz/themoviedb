package org.queiroz.themoviedb.movies.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import org.queiroz.themoviedb.databinding.ItemPageMovieInfoOverviewBinding
import org.queiroz.themoviedb.model.TmdbMovies

class MovieOverViewViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TmdbMovies.Movie?) = with(binding.root) {
        binding.setVariable(BR.movie, movie)
        binding.executePendingBindings()
    }

    companion object {

        fun create(parent: ViewGroup): MovieOverViewViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemPageMovieInfoOverviewBinding =
                ItemPageMovieInfoOverviewBinding.inflate(layoutInflater, parent, false)
            return MovieOverViewViewHolder(binding)
        }

    }
}
