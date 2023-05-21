package com.girrafeecstud.signals.auth_impl.di

import android.content.Context
import com.girrafeecstud.signals.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import retrofit2.Retrofit

interface AuthFeatureDependencies {

    fun getRetroft(): Retrofit

    fun getContext(): Context

}