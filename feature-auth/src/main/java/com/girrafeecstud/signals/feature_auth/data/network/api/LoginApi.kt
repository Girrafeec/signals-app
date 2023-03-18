package com.girrafeecstud.signals.feature_auth.data.network.api

import com.girrafeecstud.signals.feature_auth.data.network.config.AuthApiConfig
import com.girrafeecstud.signals.feature_auth.data.network.dto.UserLoginRequestDto
import com.girrafeecstud.signals.feature_auth.data.network.dto.UserLoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST(AuthApiConfig.LOGIN_API_PATH)
    suspend fun login(@Body loginRequest: UserLoginRequestDto): Response<UserLoginResponseDto>

}