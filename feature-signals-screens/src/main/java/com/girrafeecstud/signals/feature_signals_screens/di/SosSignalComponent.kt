package com.girrafeecstud.signals.feature_signals_screens.di

import com.girrafeecstud.signals.feature_signals_screens.di.annotation.SosSignalScope
import com.girrafeecstud.signals.feature_signals_screens.ui.SosSignalFragment
import dagger.Subcomponent

@SosSignalScope
@Subcomponent(modules = [SosSignalModule::class])
interface SosSignalComponent {

    fun inject(fragment: SosSignalFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SosSignalComponent

    }

}