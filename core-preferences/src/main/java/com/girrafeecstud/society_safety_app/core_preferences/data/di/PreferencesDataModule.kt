package com.girrafeecstud.society_safety_app.core_preferences.data.di

import android.content.Context
import com.girrafeecstud.society_safety_app.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import com.girrafeecstud.society_safety_app.core_preferences.data.repository.AuthSharedPreferencesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesDataModule {

    @Provides
    @Singleton
    fun provideAuthSharedPreferencesDataSource(context: Context) =
        AuthSharedPreferencesDataSource(applicationContext = context)

    @Provides
    @Singleton
    fun provideAuthSharedPreferencesRepository(dataSource: AuthSharedPreferencesDataSource) =
        AuthSharedPreferencesRepository(dataSource = dataSource)
}