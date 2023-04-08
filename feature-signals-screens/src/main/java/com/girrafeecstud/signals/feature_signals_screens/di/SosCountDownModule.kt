package com.girrafeecstud.signals.feature_signals_screens.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_signals_screens.di.annotation.SosCountDownScope
import com.girrafeecstud.signals.feature_signals_screens.presentation.SosCountDownViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SosCountDownModule {

    @SosCountDownScope
    @Binds
    @IntoMap
    @ViewModelKey(SosCountDownViewModel::class)
    fun bindSosCountDownViewModel(impl: SosCountDownViewModel): ViewModel

}
