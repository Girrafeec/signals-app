package com.girrafeecstud.signals.feature_auth.data.network.dto

import com.google.gson.annotations.SerializedName

data class UserRegistrationRequestDto(
    @SerializedName("adultPhoneNumber")
    val userPhoneNumber: String,
    @SerializedName("adultPassword")
    val userPassword: String,
    @SerializedName("adultFirstName")
    val userFirstName: String,
    @SerializedName("adultLastName")
    val userLastName: String
)