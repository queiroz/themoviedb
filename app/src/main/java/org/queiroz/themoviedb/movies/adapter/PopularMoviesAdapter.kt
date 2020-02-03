package org.queiroz.themoviedb.movies.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.movies.adapter.viewholder.MovieViewHolder
import org.queiroz.themoviedb.movies.adapter.viewholder.NetworkStateViewHolder
import org.queiroz.themoviedb.movies.viewstate.NetworkState

class PopularMoviesAdapter(
    private val retryEvent: () -> Unit,
    private val itemClickedEvent: (movie: TmdbMovies.Movie?, posterImageView: View, position: Int) -> Unit
) : PagedListAdapter<TmdbMovies.Movie, RecyclerView.ViewHolder>(COMPARATOR) {

    private var networkState: NetworkState? = null

    private fun hasExtraRow(): Boolean = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = networkState
        val hadExtraRow = hasExtraRow()
        networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent, retryEvent)
            R.layout.item_movie -> MovieViewHolder.create(parent, itemClickedEvent)
            else -> throw IllegalArgumentException("Unknown view layoutRes $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NetworkStateViewHolder -> holder.bind(networkState)
            is MovieViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_movie
        }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow()) 1 else 0

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<TmdbMovies.Movie>() {

            override fun areItemsTheSame(
                oldItem: TmdbMovies.Movie,
                newItem: TmdbMovies.Movie
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TmdbMovies.Movie,
                newItem: TmdbMovies.Movie
            ): Boolean = oldItem == newItem

        }
    }

}
