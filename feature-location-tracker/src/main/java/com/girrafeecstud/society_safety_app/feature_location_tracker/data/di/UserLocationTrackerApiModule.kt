package com.girrafeecstud.society_safety_app.feature_location_tracker.data.di

import com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.api.UserLocationTrackerApi
import com.girrafeecstud.society_safety_app.feature_location_tracker.di.annotation.LocationTrackerScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserLocationTrackerApiModule {

    @LocationTrackerScope
    @Provides
    fun bindLocationTrackerApi(retrofit: Retrofit): UserLocationTrackerApi = retrofit.create(UserLocationTrackerApi::class.java)

}