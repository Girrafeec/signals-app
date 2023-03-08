package com.girrafeecstud.society_safety_app.feature_map.di

import com.girrafeecstud.society_safety_app.feature_map.di.annotation.SosMapScope
import com.girrafeecstud.society_safety_app.feature_map.ui.SosSignalMapFragment
import dagger.Subcomponent

@SosMapScope
@Subcomponent(modules = [SosSignalMapModule::class])
interface SosMapComponent {

    fun inject(fragment: SosSignalMapFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SosMapComponent

    }

}