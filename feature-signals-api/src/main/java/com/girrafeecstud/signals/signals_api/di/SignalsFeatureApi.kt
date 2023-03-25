package com.girrafeecstud.signals.signals_api.di

import com.girrafeecstud.signals.signals_api.domain.GetSignalsListUseCase
import com.girrafeecstud.signals.signals_api.domain.IGetSignalDetailsUseCase
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine

interface SignalsFeatureApi {

    fun getGetSignalsListUseCase(): GetSignalsListUseCase

    fun getGetSignalDetailsUseCase(): IGetSignalDetailsUseCase

    fun getSignalsEngine(): SignalsEngine

}