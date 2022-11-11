package com.girrafeecstud.society_safety_app.feature_location_tracker.data.datasource

import com.girrafeecstud.society_safety_app.core_base.base.ExceptionType
import com.girrafeecstud.society_safety_app.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.api.UserLocationTrackerApi
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.mapper.UserLocationEntityDtoMapper
import com.girrafeecstud.society_safety_app.feature_location_tracker.domain.entity.UserLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class LocationTrackerDataSourceImpl @Inject constructor(
    private val api: UserLocationTrackerApi,
    private val requestMapper: UserLocationEntityDtoMapper
) : LocationTrackerDataSource {

    override suspend fun updateLocation(userId: String, location: UserLocation): Flow<BusinessResult<Nothing>> {
        return flow {

            val requestBody = requestMapper.map(input = location)

            try {
                val response = api.updateLocation(userId = userId, requestBody = requestBody)

                if (response.isSuccessful)
                    emit(BusinessResult.Success(_data = null))

                emit(BusinessResult.Error(businessErrorType = BusinessErrorType.WRONG_DATA))

            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            } catch (exception: IOException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }

        }
    }
}