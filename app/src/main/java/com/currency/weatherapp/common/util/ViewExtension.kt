package com.currency.weatherapp.common.util

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.currency.weatherapp.common.base.BaseActivity


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

fun EditText.onSubmit(func: () -> Unit) { setOnEditorActionListener { v, actionId, e ->

    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
       func.invoke()
        hideKeypad()
    return@setOnEditorActionListener true
    }

    return@setOnEditorActionListener false

}
}

fun FragmentActivity.hideKeypad(baseActivity: BaseActivity<*>) {
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(baseActivity.currentFocus?.windowToken, 0)

}


fun View.hideKeypad() {
    val imm: InputMethodManager =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

