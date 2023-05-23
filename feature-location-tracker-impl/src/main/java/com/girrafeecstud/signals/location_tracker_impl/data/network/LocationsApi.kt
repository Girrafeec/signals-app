package com.girrafeecstud.signals.location_tracker_impl.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface LocationsApi {

    @PATCH("locations")
    suspend fun updateLocation(
        @Header("Authorization") authorizationToken: String,
        @Body body: LocationsRequestDto
    ): Response<Unit>

}
