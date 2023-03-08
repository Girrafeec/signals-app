package com.girrafeecstud.society_safety_app.feature_map.di

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_map.di.annotation.MapsFeatureScope
import com.girrafeecstud.society_safety_app.feature_map.presentation.SettingsViewModel
import com.girrafeecstud.society_safety_app.feature_map.presentation.SignalsMapViewModel
import com.girrafeecstud.society_safety_app.feature_map.presentation.SosSignalMapViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [MainModule.MapsFeatureBindModule::class],
    subcomponents = [SignalsMapComponent::class, SosMapComponent::class]
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
        abstract fun bindSettingsViewModel(impl: SettingsViewModel): ViewModel
    }

}