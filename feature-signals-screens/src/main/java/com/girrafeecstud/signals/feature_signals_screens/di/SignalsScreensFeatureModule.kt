package com.girrafeecstud.signals.feature_signals_screens.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_signals_screens.di.annotation.SignalsFeatureScope
import com.girrafeecstud.signals.feature_signals_screens.presentation.SignalSharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = [SosSignalComponent::class, SosCountDownComponent::class])
interface SignalsScreensFeatureModule {

    @SignalsFeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(SignalSharedViewModel::class)
    fun bindSignalSharedViewModel(impl: SignalSharedViewModel): ViewModel

}