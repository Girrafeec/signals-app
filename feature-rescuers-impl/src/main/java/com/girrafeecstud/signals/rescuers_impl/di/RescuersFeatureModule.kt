package com.girrafeecstud.signals.rescuers_impl.di

import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuerDetailsUseCase
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine
import com.girrafeecstud.signals.rescuers_impl.data.RescuersRandomizer
import com.girrafeecstud.signals.rescuers_impl.data.datasource.RescuersDataSource
import com.girrafeecstud.signals.rescuers_impl.data.datasource.RescuersDataSourceImpl
import com.girrafeecstud.signals.rescuers_impl.data.network.RescuerDtoEntityMapper
import com.girrafeecstud.signals.rescuers_impl.data.network.RescuerDtoListMapper
import com.girrafeecstud.signals.rescuers_impl.data.network.RescuersApi
import com.girrafeecstud.signals.rescuers_impl.data.repository.RescuersRepositoryImpl
import com.girrafeecstud.signals.rescuers_impl.di.annotation.RescuersFeatureScope
import com.girrafeecstud.signals.rescuers_impl.domain.GetRescuerDetailsUseCase
import com.girrafeecstud.signals.rescuers_impl.domain.GetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_impl.domain.RescuersRepository
import com.girrafeecstud.signals.rescuers_impl.engine.RescuersEngineImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(
    includes = [RescuersFeatureModule.RescuersFeatureBindModule::class],
    subcomponents = [RescuersFeatureReceiverComponent::class]
)
class RescuersFeatureModule {

    @RescuersFeatureScope
    @Provides
    fun provideRescuerDtoEntityMapper() = RescuerDtoEntityMapper()

    @RescuersFeatureScope
    @Provides
    fun provideRescuerDtoListMapper(mapper: RescuerDtoEntityMapper) =
        RescuerDtoListMapper(rescuerDtoMapper = mapper)

    @RescuersFeatureScope
    @Provides
    fun provideRescuersApi(retrofit: Retrofit) = retrofit.create(RescuersApi::class.java)

    @RescuersFeatureScope
    @Provides
    fun provideRescuersRandomizer(
        @Named("LOCATION_TRACKER_DATASOURCE")
        locationTrackerDataSource: BaseLocationTrackerDataSource
    ) =
        RescuersRandomizer(
            locationDataSource = locationTrackerDataSource
        )

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
        fun bindGetRescuersListUseCase(impl: GetRescuersListUseCase): IGetRescuersListUseCase

        @RescuersFeatureScope
        @Binds
        fun bindGetRescuerDetailsUseCase(impl: GetRescuerDetailsUseCase): IGetRescuerDetailsUseCase

        @RescuersFeatureScope
        @Binds
        fun bindRescuersEngineImpl(impl: RescuersEngineImpl): RescuersEngine
    }

}