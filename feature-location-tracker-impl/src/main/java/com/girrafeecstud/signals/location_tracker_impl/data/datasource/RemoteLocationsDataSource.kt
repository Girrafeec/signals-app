/* Created by Girrafeec */

package com.girrafeecstud.signals.location_tracker_impl.data.datasource

import android.util.Log
import androidx.lifecycle.viewmodel.CreationExtras
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.signals.location_tracker_impl.data.network.LocationsApi
import com.girrafeecstud.signals.location_tracker_impl.data.network.UserLocationEntityDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteLocationsDataSource @Inject constructor(
    private val api: LocationsApi,
    private val mapper: UserLocationEntityDtoMapper
)  {

    suspend fun updateLocation(authToken: String, location: UserLocation): Flow<BusinessResult<EmptyResult>> =
        flow {
            Log.i("tag location", "remote ds sending")
            val requestBody = mapper.map(input = location)

            try {
                val response = api.updateLocation(authorizationToken = authToken, body = requestBody)
                val responseBody = response.body()

                Log.i("locations ds", "response code ${response.code()}")

                if (response.isSuccessful && responseBody != null) {
                    emit(BusinessResult.Success(_data = EmptyResult))
                }

            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }catch (exception: IOException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }
        }

}