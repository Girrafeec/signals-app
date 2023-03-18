package com.girrafeecstud.route_builder_api.data

import com.girrafeecstud.route_builder_api.domain.Route
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface RoutesDataSource {

    fun getRoute(route: Route): Flow<BusinessResult<Route>>

    fun getRoutes(routes: List<Route>): Flow<BusinessResult<List<Route>>>

}