package com.girrafeecstud.signals.feature_auth.domain.entity

data class UserLoginEntity(
    val userPhoneNumber: String,
    val userPassword: String
)