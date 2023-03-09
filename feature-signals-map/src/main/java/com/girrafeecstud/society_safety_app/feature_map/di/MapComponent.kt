package com.girrafeecstud.society_safety_app.feature_map.di

import com.girrafeecstud.society_safety_app.feature_map.di.annotation.MapScope
import com.girrafeecstud.society_safety_app.feature_map.ui.MapFragment
import dagger.Subcomponent

@MapScope
@Subcomponent(
    modules = [MapModule::class]
)
interface MapComponent {

    fun inject(fragment: MapFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): MapComponent

    }

}