package org.queiroz.themoviedb.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = ["date", "dateFormat"], requireAll = false)
fun formatDate(view: TextView, date: String, format: String? = null) {
    val defaultFormat = "yyyy-MM-dd"
    val newFormat = format ?: defaultFormat
    val dateFormat = SimpleDateFormat(newFormat, Locale.getDefault())
    try {
        val simpleDate = SimpleDateFormat(defaultFormat, Locale.getDefault()).parse(date)
        if (simpleDate != null) {
            view.text = dateFormat.format(simpleDate)
        }
    } catch (e: ParseException) {
        view.text = date
        Timber.e(e)
    }
}

