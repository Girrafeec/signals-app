package com.girrafeecstud.route_builder_api.data

import com.girrafeecstud.route_builder_api.domain.Location
import com.girrafeecstud.route_builder_api.domain.Route
import kotlinx.coroutines.flow.Flow

interface RouteBuilderClient {
    fun buildRoute(route: Route): Flow<Route>

    fun buildRoutes(routes: List<Route>): Flow<List<Route>>
}