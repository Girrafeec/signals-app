package com.girrafeecstud.signals.rescuers_impl.di

import com.girrafeecstud.signals.rescuers_api.domain.GetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine
import com.girrafeecstud.signals.rescuers_impl.data.datasource.RescuersDataSource
import com.girrafeecstud.signals.rescuers_impl.data.datasource.RescuersDataSourceImpl
import com.girrafeecstud.signals.rescuers_impl.data.repository.RescuersRepositoryImpl
import com.girrafeecstud.signals.rescuers_impl.di.annotation.RescuersFeatureScope
import com.girrafeecstud.signals.rescuers_impl.domain.GetRescuersListUseCaseImpl
import com.girrafeecstud.signals.rescuers_impl.domain.RescuersRepository
import com.girrafeecstud.signals.rescuers_impl.engine.RescuersEngineImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [RescuersFeatureModule.RescuersFeatureBindModule::class],
    subcomponents = [RescuersFeatureReceiverComponent::class]
)
class RescuersFeatureModule {

    @Module
    interface RescuersFeatureBindModule {

        @RescuersFeatureScope
        @Binds
        fun bindRescuersDataSourceImpl(impl: RescuersDataSourceImpl): RescuersDataSource

        @RescuersFeatureScope
        @Binds
        fun bindRescuersRepositoryImpl(impl: RescuersRepositoryImpl): RescuersRepository

        @RescuersFeatureScope
        @Binds
        fun bindGetRescuersListUseCaseImpl(impl: GetRescuersListUseCaseImpl): GetRescuersListUseCase

        @RescuersFeatureScope
        @Binds
        fun bindRescuersEngineImpl(impl: RescuersEngineImpl): RescuersEngine
    }

}