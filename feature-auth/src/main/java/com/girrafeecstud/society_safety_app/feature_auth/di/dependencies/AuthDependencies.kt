package com.girrafeecstud.society_safety_app.feature_auth.di.dependencies

import com.girrafeecstud.society_safety_app.core_network.data.network.NetworkConfig
import retrofit2.Retrofit

interface AuthDependencies {

//    val retrofit: Retrofit
    fun networkConfig(): NetworkConfig

}