package com.girrafeecstud.signals.feature_auth.domain.entity

data class UserRegistrationEntity(
    val userPhoneNumber: String,
    val userPassword: String,
    val userFirstName: String,
    val userLastName: String
)