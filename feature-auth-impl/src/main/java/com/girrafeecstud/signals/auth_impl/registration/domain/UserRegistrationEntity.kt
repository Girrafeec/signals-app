package com.girrafeecstud.signals.auth_impl.registration.domain

data class UserRegistrationEntity(
    val userPhoneNumber: String,
    val userPassword: String,
    val userFirstName: String,
    val userLastName: String
)
