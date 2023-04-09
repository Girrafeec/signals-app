package com.girrafeecstud.signals.signals_impl.di

import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.signals.rescuers_impl.di.annotation.SignalsFeatureScope
import com.girrafeecstud.signals.signals_api.domain.GetSignalsListUseCase
import com.girrafeecstud.signals.signals_api.domain.IGetSignalDetailsUseCase
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import com.girrafeecstud.signals.signals_impl.data.SignalsRandomizer
import com.girrafeecstud.signals.signals_impl.data.datasource.SignalsDataSource
import com.girrafeecstud.signals.signals_impl.data.datasource.SignalsDataSourceImpl
import com.girrafeecstud.signals.signals_impl.data.repository.SignalsRepositoryImpl
import com.girrafeecstud.signals.signals_impl.domain.GetSignalDetailsUseCase
import com.girrafeecstud.signals.signals_impl.domain.GetSignalsListUseCaseImpl
import com.girrafeecstud.signals.signals_impl.domain.SignalsRepository
import com.girrafeecstud.signals.signals_impl.engine.SignalsEngineImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(
    includes = [SignalsFeatureModule.SignalsFeatureBindModule::class],
    subcomponents = [SignalsFeatureReceiverComponent::class]
)
class SignalsFeatureModule {

    @SignalsFeatureScope
    @Provides
    fun provideSignalsRandomizer(
        @Named("LOCATION_TRACKER_DATASOURCE")
        locationTrackerDataSource: BaseLocationTrackerDataSource
    ) =
        SignalsRandomizer(
            locationDataSource = locationTrackerDataSource
        )

    @Module
    interface SignalsFeatureBindModule {

        @SignalsFeatureScope
        @Binds
        fun bindSignalsDataSourceImpl(impl: SignalsDataSourceImpl): SignalsDataSource

        @SignalsFeatureScope
        @Binds
        fun bindSignalsRepositoryImpl(impl: SignalsRepositoryImpl): SignalsRepository

        @SignalsFeatureScope
        @Binds
        fun bindGetSignalsListUseCaseImpl(impl: GetSignalsListUseCaseImpl): GetSignalsListUseCase

        @SignalsFeatureScope
        @Binds
        fun bindGetSignalDetailsUseCaseImpl(impl: GetSignalDetailsUseCase): IGetSignalDetailsUseCase


        @SignalsFeatureScope
        @Binds
        fun bindSignalsEngineImpl(impl: SignalsEngineImpl): SignalsEngine
    }

}