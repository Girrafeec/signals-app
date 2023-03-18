package com.girrafeecstud.signals.core_preferences.di

import com.girrafeecstud.signals.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import com.girrafeecstud.signals.core_preferences.data.repository.AuthSharedPreferencesRepository

interface CorePreferencesApi {

    fun authSharedPreferencesDataSource(): AuthSharedPreferencesDataSource

    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

}