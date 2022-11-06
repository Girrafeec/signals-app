package com.girrafeecstud.society_safety_app.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.society_safety_app.di.AppComponent
import com.girrafeecstud.society_safety_app.di.AppDependencies
import com.girrafeecstud.society_safety_app.di.DaggerAppComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.AuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.DaggerAuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.provider.AuthComponentProvider

class SocietySafetyApp: Application(), AuthComponentProvider {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appDependencies(AppDependenciesImpl())
            .build()
    }

    override fun getAuthComponent(): AuthComponent = DaggerAuthComponent.builder().build()

    private inner class AppDependenciesImpl: AppDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

}