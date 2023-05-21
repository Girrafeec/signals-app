package com.girrafeecstud.signals.auth_impl.login.data.network

import com.google.gson.annotations.SerializedName

data class UserLoginRequestDto(
    @SerializedName("adultPhoneNumber")
    val userPhoneNumber: String,
    @SerializedName("adultPassword")
    val userPassword: String
)
