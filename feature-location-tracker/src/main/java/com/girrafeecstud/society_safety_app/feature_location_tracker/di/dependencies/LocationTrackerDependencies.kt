package com.girrafeecstud.society_safety_app.feature_location_tracker.di.dependencies

import android.content.Context
import com.girrafeecstud.society_safety_app.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import retrofit2.Retrofit

interface LocationTrackerDependencies {

//    fun retroft(): Retrofit

//    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

//    fun authSharedPreferencesDataSource(): AuthSharedPreferencesDataSource

    val applicationContext: Context

}