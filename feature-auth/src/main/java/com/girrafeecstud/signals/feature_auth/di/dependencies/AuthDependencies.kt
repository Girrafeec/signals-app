package com.girrafeecstud.signals.feature_auth.di.dependencies

import com.girrafeecstud.signals.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import retrofit2.Retrofit

interface AuthDependencies {

    fun retroft(): Retrofit

//    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

    fun authSharedPreferencesDataSource(): AuthSharedPreferencesDataSource

//    fun mainViewModelFactory(): ViewModelProvider.Factory

}