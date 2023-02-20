package com.girrafeecstud.society_safety_app.feature_map.di.dependencies

import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.society_safety_app.core_preferences.data.repository.AuthSharedPreferencesRepository
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase

interface MainDependencies {

    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

    fun getLastKnownLocationUseCase(): GetLastKnownLocationUseCase

    fun getLocationTrackerEngine(): LocationTrackerEngine

    fun getSendSosSignalUseCase(): SendSosSignalUseCase
}