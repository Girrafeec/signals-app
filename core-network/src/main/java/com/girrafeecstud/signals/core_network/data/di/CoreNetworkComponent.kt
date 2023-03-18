package com.girrafeecstud.signals.core_network.data.di

import com.girrafeecstud.signals.core_network.data.di.module.NetworkModule
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
interface CoreNetworkComponent: CoreNetworkApi {

    @Component.Builder
    interface Builder {

        fun networkDependencies(networkDependencies: NetworkDependencies): Builder

        fun build(): CoreNetworkComponent

    }

    companion object {

        private var _coreNetworkComponent: CoreNetworkComponent? = null

        val coreNetworkComponent get() = _coreNetworkComponent!!

        fun init(networkDependencies: NetworkDependencies) {
            if (_coreNetworkComponent == null)
                _coreNetworkComponent = DaggerCoreNetworkComponent
                    .builder()
                    .networkDependencies(networkDependencies = networkDependencies)
                    .build()
        }

        fun reset() {
            _coreNetworkComponent = null
        }

    }

}