package com.girrafeecstud.society_safety_app.app

import android.app.Application
import android.content.Context
import android.util.Log
import com.girrafeecstud.society_safety_app.core_base.di.CoreBaseComponent
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_network.data.di.CoreNetworkComponent
import com.girrafeecstud.society_safety_app.core_network.data.di.DaggerCoreNetworkComponent
import com.girrafeecstud.society_safety_app.core_network.data.di.NetworkDependencies
import com.girrafeecstud.society_safety_app.di.AppComponent
import com.girrafeecstud.society_safety_app.di.AppDependencies
import com.girrafeecstud.society_safety_app.di.DaggerAppComponent

class SocietySafetyApp: Application() {

    lateinit var appComponent: AppComponent

    lateinit var networkComponent: CoreNetworkComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appDependencies(AppDependenciesImpl())
            .build()

        // TODO подумать над тем, как это делать иначе
        CoreNetworkComponent.init(NetworkDependenciesImpl())
        CoreBaseComponent.init()
    }

    private inner class AppDependenciesImpl: AppDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

    // TODO реализовать отправку контекста из appComponent
    private inner class NetworkDependenciesImpl: NetworkDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

}