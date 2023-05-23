package com.girrafeecstud.signals.location_tracker_impl.di.dependencies

import android.content.Context
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import retrofit2.Retrofit

interface LocationTrackerDependencies {

    fun getRetrofit(): Retrofit

    fun getContext(): Context

    fun getAuthDataSource(): IAuthDataSource

}