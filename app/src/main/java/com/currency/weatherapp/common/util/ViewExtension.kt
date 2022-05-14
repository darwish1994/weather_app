package com.currency.weatherapp.common.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


fun TextView.setViewText(@StringRes res: Int, vararg data: Any) {
    this.text = context.getString(res, *data)
}

fun ImageView.loadFrom(url: String) {
    Glide.with(this)
        .load(url.replace("//", "http://"))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}


fun View.toGone() {
    visibility = View.GONE
}
fun View.toVisible() {
    visibility = View.VISIBLE
}
