package com.girrafeecstud.signals.feature_signals.di

import com.girrafeecstud.signals.feature_signals.di.annotation.SosSignalScope
import com.girrafeecstud.signals.feature_signals.ui.SosSignalFragment
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