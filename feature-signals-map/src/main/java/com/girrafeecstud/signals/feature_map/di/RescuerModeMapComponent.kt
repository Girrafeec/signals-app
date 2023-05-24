/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.di

import com.girrafeecstud.signals.feature_map.ui.RescuerModeMapFragment
import dagger.Subcomponent

@RescuerModeMapScope
@Subcomponent(
    modules = [RescuerModeMapModule::class]
)
interface RescuerModeMapComponent {

    fun inject(fragment: RescuerModeMapFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): RescuerModeMapComponent

    }

}