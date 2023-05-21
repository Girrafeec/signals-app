package com.girrafeecstud.signals.auth_impl.registration.data

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