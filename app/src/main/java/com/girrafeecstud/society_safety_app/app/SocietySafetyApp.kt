package com.girrafeecstud.society_safety_app.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.society_safety_app.di.AppComponent
import com.girrafeecstud.society_safety_app.di.AppDependencies
import com.girrafeecstud.society_safety_app.di.DaggerAppComponent

class SocietySafetyApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appDependencies(AppDependenciesImpl())
            .build()
    }

    private inner class AppDependenciesImpl: AppDependencies {
        override val context: Context = this@SocietySafetyApp
    }

}