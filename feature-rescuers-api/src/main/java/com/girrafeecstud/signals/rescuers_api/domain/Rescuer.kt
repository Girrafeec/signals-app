package com.girrafeecstud.signals.rescuers_api.domain

import android.os.Parcelable
import com.girrafeecstud.route_builder_api.domain.Route
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rescuer(
    val rescuerId: String,
    val rescuerFirstName: String,
    val rescuerLastName: String,
    val rescuerPhoneNumber: String,
    val rescuerProfileImageUrl: String,
    var rescuerLocationLatitude: Double,
    var rescuerLocationLongitude: Double,
    var route: Route? = null
) : Parcelable
