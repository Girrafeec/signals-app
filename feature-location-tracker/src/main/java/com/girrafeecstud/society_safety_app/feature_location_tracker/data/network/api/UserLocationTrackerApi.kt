package com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.api

import android.location.LocationRequest
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.config.LocationTrackerApiConfig
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.dto.UserLocationRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UserLocationTrackerApi {

    @POST(LocationTrackerApiConfig.LOCATION_TRACKER_PATH)
    suspend fun updateLocation(
        @Path("id") userId: String,
        @Body requestBody: UserLocationRequestDto
    ): Response<Void>

}