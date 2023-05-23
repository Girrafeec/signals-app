/* Created by Girrafeec */

package com.girrafeecstud.sos_signal_impl.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface SosSignalsApi {

    @POST("sos-signals/new")
    suspend fun sendSosSignal(
        @Header("Authorization") authHeader: String,
        @Body body: SosSignalDto
    ): Response<Unit>

    @DELETE("sos-signals")
    suspend fun disableSosSignal(@Header("Authorization") authHeader: String): Response<Unit>

}