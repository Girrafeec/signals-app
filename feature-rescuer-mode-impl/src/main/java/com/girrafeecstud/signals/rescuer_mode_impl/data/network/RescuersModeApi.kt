/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.data.network

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RescuersModeApi {

    @POST("sos-signals/{id}/rescuers")
    suspend fun acceptSosSignal(
        @Header("Authorization") authHeader: String,
        @Path("id") signalId: String
    ): Response<Unit>

    @DELETE("sos-signals/{id}/rescuers")
    suspend fun rejectSosSignal(
        @Header("Authorization") authHeader: String,
        @Path("id") signalId: String
    ): Response<Unit>

}