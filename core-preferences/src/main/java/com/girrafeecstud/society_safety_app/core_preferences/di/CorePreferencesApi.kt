package com.girrafeecstud.society_safety_app.core_preferences.di

import com.girrafeecstud.society_safety_app.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import com.girrafeecstud.society_safety_app.core_preferences.data.repository.AuthSharedPreferencesRepository

interface CorePreferencesApi {

    fun authSharedPreferencesDataSource(): AuthSharedPreferencesDataSource

    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

}