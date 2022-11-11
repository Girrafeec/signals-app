package com.girrafeecstud.society_safety_app.feature_map.di.dependencies

import com.girrafeecstud.society_safety_app.core_preferences.data.repository.AuthSharedPreferencesRepository

interface MainDependencies {

    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

}