package com.girrafeecstud.signals.signals_impl.di

import com.girrafeecstud.signals.signals_impl.di.annotation.SignalsFeatureReceiverScope
import com.girrafeecstud.signals.signals_impl.engine.SignalsFeatureReceiver
import dagger.Component.Builder
import dagger.Subcomponent

@SignalsFeatureReceiverScope
@Subcomponent
interface SignalsFeatureReceiverComponent {

    fun injectReceiver(receiver: SignalsFeatureReceiver)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignalsFeatureReceiverComponent

    }

}