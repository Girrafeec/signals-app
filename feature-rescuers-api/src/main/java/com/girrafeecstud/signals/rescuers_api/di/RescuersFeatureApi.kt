package com.girrafeecstud.signals.rescuers_api.di

import com.girrafeecstud.signals.rescuers_api.domain.GetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine

interface RescuersFeatureApi {

    fun getGetRescuersListUseCase(): GetRescuersListUseCase

    fun getRescuersEngine(): RescuersEngine

}