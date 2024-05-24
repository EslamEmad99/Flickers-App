package com.eslam.flickers_app.util

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.eslam.flickers_app.R
import java.io.File
import java.util.Locale

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun TextView.fetchText(trim: Boolean = true): String {
    val text = this.text.toString()
    if (trim) {
        text.trim()
    }
    return text
}

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun View.hide() {
    val params = this.layoutParams
    params.height = 0
    this.layoutParams = params
}

fun View.show() {
    val params = this.layoutParams
    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
    this.layoutParams = params
}

fun TextView.setTextFromHtml(text: String) {
    this.text =
        Html.fromHtml(
            text,
            Html.FROM_HTML_MODE_COMPACT
        ).toString()
}

fun ImageView.loadImageFromUrl(url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    this.load(url) {
        crossfade(true)
//        crossfade(500)
        placeholder(circularProgressDrawable)
        error(R.drawable.ic_logo)
    }
}

fun ImageView.loadImageFromPath(path: String) {
    this.load(File(path)) {
        error(R.drawable.ic_logo)
    }
}

fun View.setBackground(@DrawableRes resId: Int) {
    this.background =
        ContextCompat.getDrawable(this.context, resId)
}

fun View.getString(@StringRes stringResId: Int): String = resources.getString(stringResId)

fun Context.getScreenWidth(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics =
            (this.getSystemService(Context.WINDOW_SERVICE) as WindowManager).currentWindowMetrics
        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.width() - insets.left - insets.right
    } else {
        val displayMetrics = DisplayMetrics()
        (this.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(
            displayMetrics
        )
        displayMetrics.widthPixels
    }
}

fun View.splitScreen(screenWidth: Int, ratio: Double) {
    val itemWidth = screenWidth / ratio
    val lp = this.layoutParams
    lp.height = lp.height
    lp.width = itemWidth.toInt()
    this.layoutParams = lp
}

fun Double.round(format: Int = 2): String {
    val locale = Locale("en", "SA")
    return "%.${format}f".format(locale, this)
}
