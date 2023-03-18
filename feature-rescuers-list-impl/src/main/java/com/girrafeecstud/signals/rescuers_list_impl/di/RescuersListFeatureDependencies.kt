package com.girrafeecstud.signals.rescuers_list_impl.di

import com.girrafeecstud.signals.rescuers_api.domain.GetRescuersListUseCase

interface RescuersListFeatureDependencies {

    fun getGetRescuersListUseCase(): GetRescuersListUseCase

}