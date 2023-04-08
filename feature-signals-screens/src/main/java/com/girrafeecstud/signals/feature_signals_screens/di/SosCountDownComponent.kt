package com.girrafeecstud.signals.feature_signals_screens.di

import com.girrafeecstud.signals.feature_signals_screens.di.annotation.SosCountDownScope
import com.girrafeecstud.signals.feature_signals_screens.ui.SosCountDownDialog
import dagger.Subcomponent

@SosCountDownScope
@Subcomponent(
    modules = [SosCountDownModule::class]
)
interface SosCountDownComponent {

    fun inject(fragment: SosCountDownDialog)

    @Subcomponent.Builder
    interface Builder {
        fun build(): SosCountDownComponent
    }

}
