package com.girrafeecstud.signals.rescuers_list_impl.di

import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine

interface RescuersListFeatureDependencies {

    fun getGetRescuersListUseCase(): IGetRescuersListUseCase

    fun getRescuersEngine(): RescuersEngine

}