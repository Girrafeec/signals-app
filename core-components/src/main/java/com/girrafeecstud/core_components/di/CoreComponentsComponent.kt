/* Created by Girrafeec */

package com.girrafeecstud.core_components.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ICoreComponentsDependencies::class]
)
interface CoreComponentsComponent : CoreComponentsApi {

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ICoreComponentsDependencies): Builder

        fun build(): CoreComponentsComponent

    }

    companion object {

        private var _coreComponentsComponent: CoreComponentsComponent? = null

        val coreComponentsComponent get() = _coreComponentsComponent!!

        fun init(dependencies: ICoreComponentsDependencies) {
            if (_coreComponentsComponent == null)
                _coreComponentsComponent = DaggerCoreComponentsComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {

        }

    }

}