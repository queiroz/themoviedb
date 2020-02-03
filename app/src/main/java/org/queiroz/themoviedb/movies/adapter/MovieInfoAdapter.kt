package org.queiroz.themoviedb.movies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.model.PageData
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.movies.adapter.viewholder.MovieCastViewHolder
import org.queiroz.themoviedb.movies.adapter.viewholder.MovieOverViewViewHolder

class MovieInfoAdapter : ListAdapter<PageData, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PAGE_OVERVIEW_LAYOUT -> MovieOverViewViewHolder.create(parent)
            PAGE_CAST_LAYOUT -> MovieCastViewHolder.create(parent)
            else -> throw IllegalArgumentException("Unknown view layoutRes $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pageData = getItem(position)
        when (holder) {
            is MovieOverViewViewHolder -> holder.bind(pageData.data as? TmdbMovies.Movie)
            is MovieCastViewHolder -> holder.bind(pageData.data as? String)
        }
    }

    override fun getItemViewType(position: Int): Int  = getItem(position).layout

    companion object {
        const val PAGE_OVERVIEW_LAYOUT = R.layout.item_page_movie_info_overview
        const val PAGE_CAST_LAYOUT = R.layout.item_page_movie_info_cast
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<PageData>() {
            override fun areItemsTheSame(oldItem: PageData, newItem: PageData): Boolean {
                return oldItem.layout == newItem.layout
            }

            override fun areContentsTheSame(oldItem: PageData, newItem: PageData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
