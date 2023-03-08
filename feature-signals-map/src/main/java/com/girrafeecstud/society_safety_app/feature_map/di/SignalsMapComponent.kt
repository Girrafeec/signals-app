package com.girrafeecstud.society_safety_app.feature_map.di

import com.girrafeecstud.society_safety_app.feature_map.di.annotation.SignalsMapScope
import com.girrafeecstud.society_safety_app.feature_map.ui.MapFragment
import dagger.Subcomponent

@SignalsMapScope
@Subcomponent(modules = [SignalsMapModule::class])
interface SignalsMapComponent {

    fun inject(fragment: MapFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignalsMapComponent

    }

}
