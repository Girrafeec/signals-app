package com.girrafeecstud.signals.feature_map.di

import com.girrafeecstud.signals.feature_map.di.annotation.SignalsMapScope
import com.girrafeecstud.signals.feature_map.ui.SignalsMapFragment
import dagger.Subcomponent

@SignalsMapScope
@Subcomponent(modules = [SignalsMapModule::class])
interface SignalsMapComponent {

    fun inject(fragment: SignalsMapFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignalsMapComponent

    }

}
