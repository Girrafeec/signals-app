package com.girrafeecstud.signals.signals_impl

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RescuersFeatureUnitTests {

    private lateinit var locationTrackerDataSource: LocationTrackerDataSource
    private lateinit var rescuersDataSource: RescuersDataSource
    private lateinit var routesDataSource: RoutesDataSource

    private lateinit var repository: RescuersRepository

    @Before
    fun setUp() {
        locationTrackerDataSource = mock()
        rescuersDataSource = mock()
        routesDataSource = mock()
        repository = RescuersRepositoryImpl(
            locationTrackerDataSource = locationTrackerDataSource,
            rescuersDataSource = rescuersDataSource,
            routesDataSource = routesDataSource
        )
    }

    @Test
    fun `EXPECT success result rescuers list with routes`() {
        runBlocking {
            val locationResult = BusinessResult.Success(_data = location)
            val rescuersResult = BusinessResult.Success(_data = rescuers)
            val routeResult = BusinessResult.Success(_data = defaultRoutes)

            val rescuersWithRoutsResult = BusinessResult.Success(_data = builtRoutes)

            whenever(locationTrackerDataSource.getLastKnownLocation()).thenReturn(
                flowOf(locationResult)
            )
            whenever(rescuersDataSource.getRescuersList(token = "")).thenReturn(
                flowOf(rescuersResult)
            )
            whenever(routesDataSource.getRoutes(any())).thenReturn(flowOf(routeResult))

            // Call the function being tested
            val result = repository.getRescuersList().toList()

            // Check the result
            assertEquals(listOf(rescuersWithRoutsResult), result)
        }
    }

    @Test
    fun `EXPECT GPS_NOT_ENABLED result`() {
        runBlocking {
            val rescuersResult = BusinessResult.Success(_data = rescuers)
            val routeResult = BusinessResult.Success(_data = defaultRoutes)

            whenever(locationTrackerDataSource.getLastKnownLocation()).thenReturn(
                flowOf(gpsNotEnabledResultResult)
            )
            whenever(rescuersDataSource.getRescuersList(token = "")).thenReturn(
                flowOf(rescuersResult)
            )
            whenever(routesDataSource.getRoutes(any())).thenReturn(
                flowOf(routeResult)
            )

            // Call the function being tested
            val result = repository.getRescuersList().toList().last()

            // Check the result
            assertEquals(gpsNotEnabledResultResult, result)
        }
    }

    @Test
    fun `LOCATION_PERMISSIONS_NOT_GRANTED result`() {
        runBlocking {
            val routeResult = BusinessResult.Success(_data = defaultRoutes)

            whenever(locationTrackerDataSource.getLastKnownLocation()).thenReturn(
                flowOf(locationPermissionsNotGrantedResult)
            )
            whenever(rescuersDataSource.getRescuersList(token = "")).thenReturn(
                flowOf(noInternetConnectionResult)
            )
            whenever(routesDataSource.getRoutes(any())).thenReturn(
                flowOf(routeResult)
            )

            // Call the function being tested
            val result = repository.getRescuersList().toList().last()

            // Check the result
            assertEquals(locationPermissionsNotGrantedResult, result)
        }
    }

    @Test
    fun `EXPECT ROUTE_BUILDING_ERROR result`() {
        runBlocking {
            val locationResult = BusinessResult.Success(_data = location)
            val rescuersResult = BusinessResult.Success(_data = rescuers)

            whenever(locationTrackerDataSource.getLastKnownLocation()).thenReturn(
                flowOf(locationResult)
            )
            whenever(rescuersDataSource.getRescuersList(token = "")).thenReturn(
                flowOf(rescuersResult)
            )
            whenever(routesDataSource.getRoutes(any())).thenReturn(
                flowOf(routeBuildingErrorResult)
            )

            // Call the function being tested
            val result = repository.getRescuersList().toList().last()

            // Check the result
            assertEquals(routeBuildingErrorResult, result)
        }
    }

    @Test
    fun `EXPECT NO_INTERNET_CONNECTION result`() {
        runBlocking {
            val locationResult = BusinessResult.Success(_data = location)
            val routeResult = BusinessResult.Success(_data = defaultRoutes)

            whenever(locationTrackerDataSource.getLastKnownLocation()).thenReturn(
                flowOf(locationResult)
            )
            whenever(rescuersDataSource.getRescuersList(token = "")).thenReturn(
                flowOf(noInternetConnectionResult)
            )
            whenever(routesDataSource.getRoutes(any())).thenReturn(
                flowOf(routeResult)
            )

            // Call the function being tested
            val result = repository.getRescuersList().toList().last()

            // Check the result
            assertEquals(noInternetConnectionResult, result)
        }
    }

    @Test
    fun `EXPECT INTERNET_CONNECTION_TIMEOUT`() {
        runBlocking {
            val locationResult = BusinessResult.Success(_data = location)
            val routeResult = BusinessResult.Success(_data = defaultRoutes)

            whenever(locationTrackerDataSource.getLastKnownLocation()).thenReturn(
                flowOf(locationResult)
            )
            whenever(rescuersDataSource.getRescuersList(token = "")).thenReturn(
                flowOf(internetConnectionTimeoutResult)
            )
            whenever(routesDataSource.getRoutes(any())).thenReturn(
                flowOf(routeResult)
            )

            // Call the function being tested
            val result = repository.getRescuersList().toList().last()

            // Check the result
            assertEquals(internetConnectionTimeoutResult, result)
        }
    }

}