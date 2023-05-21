package com.girrafeecstud.signals.auth_impl.login.domain

data class UserLoginEntity(
    val userPhoneNumber: String,
    val userPassword: String
)