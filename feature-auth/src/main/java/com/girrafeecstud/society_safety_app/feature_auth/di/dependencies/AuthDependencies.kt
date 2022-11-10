package com.girrafeecstud.society_safety_app.feature_auth.di.dependencies

import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.society_safety_app.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import com.girrafeecstud.society_safety_app.core_preferences.data.repository.AuthSharedPreferencesRepository
import retrofit2.Retrofit

interface AuthDependencies {

    fun retroft(): Retrofit

//    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

    fun authSharedPreferencesDataSource(): AuthSharedPreferencesDataSource

//    fun mainViewModelFactory(): ViewModelProvider.Factory

}