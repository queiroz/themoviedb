package org.queiroz.themoviedb.movies.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_network_state.view.*
import org.queiroz.themoviedb.databinding.ItemNetworkStateBinding
import org.queiroz.themoviedb.movies.viewstate.NetworkState
import org.queiroz.themoviedb.movies.viewstate.Status

class NetworkStateViewHolder(
    private val binding: ItemNetworkStateBinding,
    private val retryEvent: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(networkState: NetworkState?) = with(binding.root) {
        retry_btn.visibility = toVisibility(networkState?.status == Status.FAILED)
        error_message.visibility = toVisibility(networkState?.msg != null)
        error_message.text = networkState?.msg
        retry_btn.setOnClickListener { retryEvent() }
    }

    companion object {

        fun create(parent: ViewGroup, retryEvent: () -> Unit): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemNetworkStateBinding =
                ItemNetworkStateBinding.inflate(layoutInflater, parent, false)
            return NetworkStateViewHolder(binding, retryEvent)
        }

        fun toVisibility(constraint: Boolean): Int = if (constraint) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}
