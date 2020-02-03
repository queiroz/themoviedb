package org.queiroz.themoviedb.util

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import az.plainpie.PieView
import org.queiroz.themoviedb.R

fun PieView.setPopularityBgColor(votes: Float) {
    val voteAverage = votes * 10
    when {
        voteAverage >= 75 -> {
            setPercentageBackgroundColor(getColor(R.color.green))
        }
        voteAverage >= 50 -> {
            setPercentageBackgroundColor(getColor(R.color.blueLight))
        }
        voteAverage >= 25 -> {
            setPercentageBackgroundColor(getColor(R.color.orange))
        }
        voteAverage < 25 -> {
            setPercentageBackgroundColor(getColor(R.color.red))
        }
    }
}

fun View.getColor(@ColorRes color: Int): Int = ResourcesCompat.getColor(resources, color, null)

fun View.debounceClick(debounceTime: Long = 1000L, action: () -> Unit) {
    setOnClickListener {
        when {
            tag != null && (tag as Long) > System.currentTimeMillis() -> return@setOnClickListener
            else -> {
                tag = System.currentTimeMillis() + debounceTime
                action()
            }
        }
    }
}
