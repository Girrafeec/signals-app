package com.girrafeecstud.signals.rescuer_details_impl.di

import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuerDetailsUseCase

interface RescuerDetailsFeatureDependencies {

    fun getGetRescuerDetailsUseCase(): IGetRescuerDetailsUseCase

}