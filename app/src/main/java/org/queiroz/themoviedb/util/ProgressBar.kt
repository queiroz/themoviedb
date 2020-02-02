package org.queiroz.themoviedb.util

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_toolbar.*
import org.queiroz.themoviedb.movies.viewstate.NetworkState

fun Activity.progressBar(networkState: NetworkState) = main_progress_bar?.apply {
    visibility = if (networkState == NetworkState.LOADING) View.VISIBLE else View.GONE
}

fun Fragment.progressBar(networkState: NetworkState) = activity?.progressBar(networkState)
