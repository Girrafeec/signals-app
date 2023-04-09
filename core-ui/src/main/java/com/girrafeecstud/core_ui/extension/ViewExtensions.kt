package com.girrafeecstud.core_ui.extension

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.girrafeecstud.signals.core_base.R
import com.squareup.picasso.Picasso

fun ImageView.loadAndSetImage(url: String?) {
    if (url == null)
        return
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.default_recktangle)
        .into(this)
}

fun View.isKeyboardVisible(): Boolean {
    val rootView = this.rootView
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val screenHeight = rootView.height
    val keyboardHeight = screenHeight - rect.bottom

    return keyboardHeight > screenHeight * 0.15
}

fun View.addOnKeyboardVisibilityListener(onKeyboardVisibilityChanged: (Boolean) -> Unit) {
    onKeyboardVisibilityChanged(this.isKeyboardVisible())
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}

//TODO rename it!!
fun View.removeView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.enable() {
    this.isEnabled = true
    this.isClickable = true
}

fun View.disable() {
    this.isEnabled = false
    this.isClickable = false
}

fun View.setElevation(elevation: Float = DEFAULT_ELEVATION) {
    this.elevation = elevation
}

fun View.resetElevation() {
    this.elevation = 0.0f
}

val DEFAULT_ELEVATION = 2.0f