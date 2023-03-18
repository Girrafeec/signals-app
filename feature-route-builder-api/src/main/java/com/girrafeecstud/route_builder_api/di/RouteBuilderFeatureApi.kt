package com.girrafeecstud.route_builder_api.di

import com.girrafeecstud.route_builder_api.data.RouteBuilderClient
import com.girrafeecstud.route_builder_api.data.RoutesDataSource

interface RouteBuilderFeatureApi {

    fun getRouteBuilderClient(): RouteBuilderClient

    fun getRoutesDataSource(): RoutesDataSource
}