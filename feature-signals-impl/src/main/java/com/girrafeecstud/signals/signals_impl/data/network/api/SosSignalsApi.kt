/* Created by Girrafeec */

package com.girrafeecstud.signals.signals_impl.data.network.api

import com.girrafeecstud.signals.signals_impl.data.network.dto.EmergencySignalDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SosSignalsApi {

    @GET("sos-signals/near")
    suspend fun getSignalsNear(
        @Header("Authorization") authHeader: String
    ): Response<List<EmergencySignalDto>>

    @GET("sos-signals/near/{id}")
    suspend fun getSignalDetails(
        @Header("Authorization") authHeader: String,
        @Path("id") signalId: String
    ): Response<EmergencySignalDto>

}