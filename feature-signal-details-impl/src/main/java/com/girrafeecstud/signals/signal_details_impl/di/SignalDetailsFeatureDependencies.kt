package com.girrafeecstud.signals.signal_details_impl.di

import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine
import com.girrafeecstud.signals.signals_api.domain.IGetSignalDetailsUseCase
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine

interface SignalDetailsFeatureDependencies {

    fun getSignalsEngine(): SignalsEngine

    fun getGetSignalDetailsUseCase(): IGetSignalDetailsUseCase

    fun getRescuerModeEngine(): IRescuerModeEngine

}