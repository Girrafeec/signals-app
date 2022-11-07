package com.girrafeecstud.society_safety_app.core_network.data.di

import com.girrafeecstud.society_safety_app.core_network.data.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
              ],
    dependencies = [
        NetworkDependencies::class
    ]
)
interface CoreNetworkComponent {

    @Component.Builder
    interface Builder {

        fun networkDependencies(networkDependencies: NetworkDependencies): Builder

        fun build(): CoreNetworkComponent

    }

}