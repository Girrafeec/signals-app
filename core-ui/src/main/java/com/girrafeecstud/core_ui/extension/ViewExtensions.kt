package com.girrafeecstud.core_ui.extension

import android.widget.ImageView
import com.girrafeecstud.signals.core_base.R
import com.squareup.picasso.Picasso

fun ImageView.loadAndSetImage(url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.default_recktangle)
        .into(this)
}