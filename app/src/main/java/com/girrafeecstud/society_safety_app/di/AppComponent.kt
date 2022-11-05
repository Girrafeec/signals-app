package com.girrafeecstud.society_safety_app.di

import com.girrafeecstud.society_safety_app.core_network.data.di.module.NetworkModule
import dagger.Component

@Component(
    modules = [
        NetworkModule::class
              ],
    dependencies = [
        AppDependencies::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun appDependencies(appDependencies: AppDependencies): Builder

        fun build(): AppComponent
    }

}