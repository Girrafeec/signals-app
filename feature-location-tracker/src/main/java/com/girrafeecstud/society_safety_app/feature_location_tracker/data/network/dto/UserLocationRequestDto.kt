package com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.dto

import com.google.gson.annotations.SerializedName

data class UserLocationRequestDto(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)
