package com.girrafeecstud.signals.rescuers_impl.data.repository

import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.route_builder_api.data.RoutesDataSource
import com.girrafeecstud.route_builder_api.domain.Location
import com.girrafeecstud.route_builder_api.domain.Route
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.rescuers_impl.data.datasource.RescuersDataSource
import com.girrafeecstud.signals.rescuers_impl.domain.RescuersRepository
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class RescuersRepositoryImpl @Inject constructor(
    private val rescuersDataSource: RescuersDataSource,
    @Named("LOCATION_TRACKER_DATASOURCE")
    private val locationTrackerDataSource: BaseLocationTrackerDataSource,
    private val routesDataSource: RoutesDataSource
) : RescuersRepository {

    override fun getRescuersList(): Flow<BusinessResult<List<Rescuer>>> =
        combine(
            locationTrackerDataSource.getLastKnownLocation().take(1),
            rescuersDataSource.getRescuersList(token = "")
        ) { locationResult, rescuersResult ->
            Pair(locationResult, rescuersResult)
        }
            .flatMapLatest { (locationResult, rescuersResult) ->
                when (locationResult) {
                    is BusinessResult.Success -> {
                        when (rescuersResult) {
                            is BusinessResult.Success -> {
                                val currentLocation = locationResult._data!!
                                var rescuers = rescuersResult._data!!
                                var routes = createRoutes(currentLocation, rescuers)
                                routesDataSource.getRoutes(routes = routes)
                                    .map { routeResult ->
                                        when (routeResult) {
                                            is BusinessResult.Success -> {
                                                val newRoutes = routeResult._data!!
                                                rescuers = assignRoutes(rescuers, newRoutes)
                                                BusinessResult.Success(rescuers)
                                            }
                                            is BusinessResult.Error -> BusinessResult.Error(routeResult.businessErrorType)
                                            is BusinessResult.Exception -> BusinessResult.Exception(routeResult.exceptionType)
                                        }
                                    }
                                    .flowOn(Dispatchers.IO)
                            }
                            is BusinessResult.Error -> flowOf(BusinessResult.Error(rescuersResult.businessErrorType))
                            is BusinessResult.Exception -> flowOf(BusinessResult.Exception(rescuersResult.exceptionType))
                        }
                    }
                    is BusinessResult.Error -> flowOf(BusinessResult.Error(locationResult.businessErrorType))
                    is BusinessResult.Exception -> flowOf(BusinessResult.Exception(locationResult.exceptionType))
                }
            }

    override fun getRescuerDetails(rescuerId: String): Flow<BusinessResult<Rescuer>> =
        rescuersDataSource.getRescuerDetails(token = "", rescuerId = rescuerId).flowOn(Dispatchers.IO)

    private fun createRoutes(
        location: UserLocation?,
        rescuers: List<Rescuer>?
    ): List<Route> {
        val routes = mutableListOf<Route>()
        if (location != null && rescuers != null) {
            for (rescuer in rescuers) {
                routes.add(
                    Route(
                        startPoint = Location(
                            latitude = rescuer.rescuerLocationLatitude,
                            longitude = rescuer.rescuerLocationLongitude
                        ),
                        endPoint = Location(
                            latitude = location.latitude,
                            longitude = location.longitude
                        )
                    )
                )
            }
        }
        return routes
    }

    private fun assignRoutes(
        rescuers: List<Rescuer>?,
        routes: List<Route>?
    ): List<Rescuer> {
        if (rescuers == null || routes == null) {
            return emptyList()
        }
        for (i in 0..routes.size-1) {
            rescuers[i].route = routes[i]
        }
        return rescuers
    }

}
