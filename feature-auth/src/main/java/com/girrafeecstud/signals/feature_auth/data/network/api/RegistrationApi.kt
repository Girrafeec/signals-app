package com.girrafeecstud.signals.feature_auth.data.network.api

import com.girrafeecstud.signals.feature_auth.data.network.config.AuthApiConfig
import com.girrafeecstud.signals.feature_auth.data.network.dto.UserRegistrationRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {

    // TODO change return rype to Response<Nothing>
    @POST(AuthApiConfig.REGISTRATION_API_PATH)
    suspend fun registration(@Body registrationRequest: UserRegistrationRequestDto): Response<Void>

}