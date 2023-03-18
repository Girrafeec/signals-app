package com.girrafeecstud.route_builder_api.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val latitude: Double,
    val longitude: Double
) : Parcelable
