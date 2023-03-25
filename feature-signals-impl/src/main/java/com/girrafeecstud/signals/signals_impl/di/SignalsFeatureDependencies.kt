package com.girrafeecstud.signals.signals_impl.di

import retrofit2.Retrofit

interface SignalsFeatureDependencies {

    fun getRetrofit(): Retrofit

}