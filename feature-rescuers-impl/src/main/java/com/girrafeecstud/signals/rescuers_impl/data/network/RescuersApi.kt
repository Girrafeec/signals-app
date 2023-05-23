/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuers_impl.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RescuersApi {

    @GET("rescuers")
    suspend fun getRescuersList(
        @Header("Authorization") authHeader: String
    ): Response<List<RescuerDto>>

    @GET("rescuers/{id}")
    suspend fun getRescuerDetails(
        @Header("Authorization") authHeader: String,
        @Path("id") rescuerId: String
    ): Response<RescuerDto>

}