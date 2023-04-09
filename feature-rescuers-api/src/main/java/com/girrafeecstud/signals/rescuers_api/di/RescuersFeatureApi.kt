package com.girrafeecstud.signals.rescuers_api.di

import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuerDetailsUseCase
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine

interface RescuersFeatureApi {

    fun getGetRescuersListUseCase(): IGetRescuersListUseCase

    fun getGetRescuerDetailsUseCase(): IGetRescuerDetailsUseCase

    fun getRescuersEngine(): RescuersEngine

}