package com.girrafeecstud.signals.core_network.data.di.module

import com.girrafeecstud.signals.core_network.data.di.annotation.BaseApiUrl
import com.girrafeecstud.signals.core_network.data.network.BaseUrlConfig
import com.girrafeecstud.signals.core_network.data.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

// TODO решить вопрос с scope зависимостей модуля

@Module
class NetworkModule {

    @Provides
    @BaseApiUrl
    fun provideBaseApiUrl() = BaseUrlConfig.BASE_API_URL

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideScalarsConverterFactory(): ScalarsConverterFactory = ScalarsConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        scalarsConverterFactory: ScalarsConverterFactory,
        okHttpClient: OkHttpClient,
        @BaseApiUrl baseApiUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .addConverterFactory(scalarsConverterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

}