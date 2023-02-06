package com.girrafeecstud.society_safety_app.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_map.presentation.SettingsViewModel
import com.girrafeecstud.society_safety_app.feature_map.presentation.SignalsMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(impl: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignalsMapViewModel::class)
    abstract fun bindSignalsMapViewModel(impl: SignalsMapViewModel): ViewModel

}