package com.girrafeecstud.signals.rescuer_details_impl.di

import com.girrafeecstud.signals.rescuer_details_api.di.RescuerDetailsFeatureApi
import com.girrafeecstud.signals.rescuer_details_impl.di.annotation.RescuerDetailsFeatureScope
import com.girrafeecstud.signals.rescuer_details_impl.ui.RescuerDetailsFragmentImpl
import dagger.Component

@RescuerDetailsFeatureScope
@Component(
    modules = [RescuerDetailsFeatureModule::class]
)
interface RescuerDetailsFeatureComponent : RescuerDetailsFeatureApi {

    fun inject(fragment: RescuerDetailsFragmentImpl)

    @Component.Builder
    interface Builder {

//        fun dependencies(dependencies: RescuerDetailsFeatureDependencies): Builder

        fun build(): RescuerDetailsFeatureComponent

    }

    companion object {

        private var _rescuerDetailsFeatureComponent: RescuerDetailsFeatureComponent? = null

        val rescuerDetailsFeatureComponent get() = _rescuerDetailsFeatureComponent!!

        fun init() {
            if (_rescuerDetailsFeatureComponent == null) {
                _rescuerDetailsFeatureComponent = DaggerRescuerDetailsFeatureComponent
                    .builder()
                    .build()
            }
        }

        fun reset() {
            _rescuerDetailsFeatureComponent = null
        }

    }

}