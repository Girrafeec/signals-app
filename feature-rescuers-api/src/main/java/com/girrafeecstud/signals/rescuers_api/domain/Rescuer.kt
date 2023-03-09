package com.girrafeecstud.signals.rescuers_api.domain

data class Rescuer(
    val rescuerId: String,
    val rescuerFirstName: String,
    val rescuerLastName: String,
    val rescuerPhoneNumber: String,
    val rescuerLocationLatitude: Double,
    val rescuerLocationLongitude: Double
)
