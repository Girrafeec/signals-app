package com.girrafeecstud.signals.rescuers_list_impl.di

import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase

interface RescuersListFeatureDependencies {

    fun getGetRescuersListUseCase(): IGetRescuersListUseCase

}