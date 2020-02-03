package org.queiroz.themoviedb.movies.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import org.queiroz.themoviedb.databinding.ItemPageMovieInfoCastBinding

class MovieCastViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: String?) = with(binding.root) {
        binding.setVariable(BR.movie, movie)
        binding.executePendingBindings()
    }

    companion object {

        fun create(parent: ViewGroup): MovieCastViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemPageMovieInfoCastBinding =
                ItemPageMovieInfoCastBinding.inflate(layoutInflater, parent, false)
            return MovieCastViewHolder(binding)
        }

    }
}
