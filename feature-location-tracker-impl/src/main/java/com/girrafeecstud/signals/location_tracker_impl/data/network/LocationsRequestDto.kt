package com.girrafeecstud.signals.location_tracker_impl.data.network

import com.google.gson.annotations.SerializedName

data class LocationsRequestDto(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
