package com.girrafeecstud.society_safety_app.core_base.di

import com.girrafeecstud.society_safety_app.core_base.presentation.base.di.BaseViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [BaseViewModelFactoryModule::class]
)
interface CoreBaseComponent {

    @Component.Builder
    interface Builder {

        fun build(): CoreBaseComponent

    }

    companion object {

        private var _coreBaseComponent: CoreBaseComponent? = null

        val coreBaseComponent get() = _coreBaseComponent!!

        fun init() {
            if (_coreBaseComponent == null)
                _coreBaseComponent = DaggerCoreBaseComponent
                    .builder()
                    .build()
        }

        fun reset() {
            _coreBaseComponent = null
        }

    }

}