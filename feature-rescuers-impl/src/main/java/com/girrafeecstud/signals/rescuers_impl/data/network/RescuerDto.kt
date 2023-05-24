package com.girrafeecstud.signals.rescuers_impl.data.network

import com.google.gson.annotations.SerializedName

data class RescuerDto(
    @SerializedName("rescuerId")
    val rescuerId: String,
    @SerializedName("firstName")
    val rescuerFirstName: String,
    @SerializedName("lastName")
    val rescuerLastName: String,
    @SerializedName("rescuerPhoneNumber")
    val rescuerPhoneNumber: String,
    @SerializedName("rescuerProfileImageUrl")
    val rescuerProfileImageUrl: String,
    @SerializedName("rescuerLocationLatitude")
    var rescuerLocationLatitude: Double,
    @SerializedName("rescuerLocationLongitude")
    var rescuerLocationLongitude: Double,
)
