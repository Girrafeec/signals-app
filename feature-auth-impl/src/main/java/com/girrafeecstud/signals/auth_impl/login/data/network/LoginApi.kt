package com.girrafeecstud.signals.auth_impl.login.data.network

import com.girrafeecstud.signals.auth_impl.auth_common.AuthApiConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST(AuthApiConfig.LOGIN_API_PATH)
    suspend fun login(@Body loginRequest: UserLoginRequestDto): Response<String>

}