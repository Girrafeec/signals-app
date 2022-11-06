package com.girrafeecstud.society_safety_app.feature_auth.data.network.dto

import com.google.gson.annotations.SerializedName

data class UserLoginResponseDto(
    @SerializedName("adultId")
    val userId: String
)
