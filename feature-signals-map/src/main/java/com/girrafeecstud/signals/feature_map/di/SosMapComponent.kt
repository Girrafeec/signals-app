package com.girrafeecstud.signals.feature_map.di

import com.girrafeecstud.signals.feature_map.di.annotation.SosMapScope
import com.girrafeecstud.signals.feature_map.ui.SosMapFragment
import dagger.Subcomponent

@SosMapScope
@Subcomponent(
    modules = [SosMapModule::class],
)
interface SosMapComponent {

    fun inject(fragment: SosMapFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SosMapComponent

    }

}