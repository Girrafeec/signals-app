package com.girrafeecstud.signals.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_map.di.annotation.MapsFeatureScope
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedViewModel
import com.girrafeecstud.signals.feature_map.presentation.SettingsViewModel
import com.girrafeecstud.signals.feature_map.presentation.SignalsMapSharedViewModel
import com.girrafeecstud.signals.feature_map.presentation.SosMapSharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [MainModule.MapsFeatureBindModule::class],
    subcomponents = [MapComponent::class, SignalsMapComponent::class, SosMapComponent::class]
)
class MainModule {

//    @Provides
//    @MapsFeatureScope
//    fun provideMapsFeatureNavController(context: Context): NavController {
//        return (context as Activity).su
//    }

    @Module
    interface MapsFeatureBindModule {
        @Binds
        @IntoMap
        @MapsFeatureScope
        @ViewModelKey(SettingsViewModel::class)
        fun bindSettingsViewModel(impl: SettingsViewModel): ViewModel

        @Binds
        @IntoMap
        @MapsFeatureScope
        @ViewModelKey(MapSharedViewModel::class)
        fun bindMapSharedViewModel(impl: MapSharedViewModel): ViewModel

        @Binds
        @IntoMap
        @MapsFeatureScope
        @ViewModelKey(SignalsMapSharedViewModel::class)
        fun bindSignalsMapSharedViewModel(impl: SignalsMapSharedViewModel): ViewModel

        @Binds
        @IntoMap
        @MapsFeatureScope
        @ViewModelKey(SosMapSharedViewModel::class)
        fun bindSosMapSharedViewModel(impl: SosMapSharedViewModel): ViewModel

        // It is here because both SosMap and SignalsMap needs this view model
//        @Binds
//        @IntoMap
//        @MapsFeatureScope
//        @ViewModelKey(MapViewModel::class)
//        fun bindMapViewModel(impl: MapViewModel): ViewModel
    }

}