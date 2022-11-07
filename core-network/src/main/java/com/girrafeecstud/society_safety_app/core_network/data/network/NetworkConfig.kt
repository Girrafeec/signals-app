package com.girrafeecstud.society_safety_app.core_network.data.network

import com.girrafeecstud.society_safety_app.core_network.data.di.annotation.BaseApiUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {

    fun retrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
        @BaseApiUrl baseApiUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

}