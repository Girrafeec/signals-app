package com.girrafeecstud.route_builder_api.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Route(
    val startPoint: Location,
    val endPoint: Location,
    var routePoints: List<Location>? = null
): Parcelable
