package com.girrafeecstud.route_builder_impl.data

import com.girrafeecstud.route_builder_api.data.RouteBuilderClient
import com.girrafeecstud.route_builder_api.data.RoutesDataSource
import com.girrafeecstud.route_builder_api.domain.Route
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.RouteBuildingException
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RoutesDataSourceImpl @Inject constructor(
    private val client: RouteBuilderClient
) : RoutesDataSource {

        override fun getRoute(route: Route): Flow<BusinessResult<Route>> =
            channelFlow {
                val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
                client.buildRoute(route = route)
                    .catch { e->
                        when (e) {
                            is RouteBuildingException -> {
                                send(BusinessResult.Exception(exceptionType = ExceptionType.ROUTE_BUILDING_ERROR))
                            }
                        }

                    }
                    .onEach { route ->
                        send(BusinessResult.Success(_data = route))
                    }
                    .launchIn(scope)
                awaitClose()
            }

    override fun getRoutes(routes: List<Route>): Flow<BusinessResult<List<Route>>> =
        channelFlow {
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
            client.buildRoutes(routes = routes)
                .catch { e->
                    when (e) {
                        is RouteBuildingException -> {
                            send(BusinessResult.Exception(exceptionType = ExceptionType.ROUTE_BUILDING_ERROR))
                        }
                    }

                }
                .onEach { routes ->
                    send(BusinessResult.Success(_data = routes))
                }
                .launchIn(scope)
            awaitClose()
        }

}