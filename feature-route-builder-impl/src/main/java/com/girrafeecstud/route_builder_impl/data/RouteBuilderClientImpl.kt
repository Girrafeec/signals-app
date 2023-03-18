package com.girrafeecstud.route_builder_impl.data

import com.girrafeecstud.route_builder_api.data.RouteBuilderClient
import com.girrafeecstud.route_builder_api.domain.Location
import com.girrafeecstud.route_builder_api.domain.Route
import com.girrafeecstud.signals.core_base.base.RouteBuildingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.Road
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

class RouteBuilderClientImpl @Inject constructor(
    private val roadManager: OSRMRoadManager
) : RouteBuilderClient {
    override fun buildRoute(route: Route): Flow<Route> =
        flow {

            val road = roadManager.getRoad(
                ArrayList(listOf(route.startPoint, route.endPoint).map { location ->
                    GeoPoint(location.latitude, location.longitude)
                })
            )

            if (!road.mStatus.equals(Road.STATUS_OK))
                throw RouteBuildingException

            route.routePoints = road.mRouteHigh.map {
                    geoPoint -> Location(latitude =geoPoint.latitude, longitude = geoPoint.longitude)
            }

            emit(route)

        }.flowOn(Dispatchers.IO)

    override fun buildRoutes(routes: List<Route>): Flow<List<Route>> =
        flow {
            val roads = ArrayList<Road>()
            for (route in routes) {
                val road = roadManager.getRoad(
                    ArrayList(listOf(route.startPoint, route.endPoint).map { location ->
                        GeoPoint(location.latitude, location.longitude)
                    })
                )
                roads.add(road)
            }

            val updatedRoutes = routes.mapIndexed { index, route ->
                route.copy(routePoints = roads[index].mRouteHigh.map { geoPoint ->
                    Location(latitude = geoPoint.latitude, longitude = geoPoint.longitude)
                })
            }

            emit(updatedRoutes)

        }.flowOn(Dispatchers.IO)
}