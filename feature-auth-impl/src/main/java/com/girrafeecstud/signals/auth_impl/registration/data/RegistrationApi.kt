package com.girrafeecstud.signals.auth_impl.registration.data

import com.girrafeecstud.signals.auth_impl.auth_common.AuthApiConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {

    // TODO change return rype to Response<Nothing>
    @POST(AuthApiConfig.REGISTRATION_API_PATH)
    suspend fun registration(@Body registrationRequest: UserRegistrationRequestDto): Response<Void>

}