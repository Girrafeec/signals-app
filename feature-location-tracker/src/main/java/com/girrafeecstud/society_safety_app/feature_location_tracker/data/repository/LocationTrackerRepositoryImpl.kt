package com.girrafeecstud.society_safety_app.feature_location_tracker.data.repository

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.datasource.LocationTrackerDataSource
import com.girrafeecstud.society_safety_app.feature_location_tracker.domain.entity.UserLocation
import com.girrafeecstud.society_safety_app.feature_location_tracker.domain.repository.LocationTrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationTrackerRepositoryImpl @Inject constructor(
    private val locationTrackerDataSource: LocationTrackerDataSource,
    private val authSharedPreferencesDataSource: AuthSharedPreferencesDataSource
) : LocationTrackerRepository {

    override suspend fun updateLocation(location: UserLocation): Flow<BusinessResult<Nothing>> =
        flow {
            val result = authSharedPreferencesDataSource.getUserId()
            when (result) {
                is BusinessResult.Success -> {
                    //TODO решить вопрос с notnull
                    val updateLocationResult =
                        locationTrackerDataSource.updateLocation(userId = result._data!!, location = location)

                    //TODO насколько так делать хорошо?
                    updateLocationResult.collect { result ->
                        emit(result)
                    }
                }
                is BusinessResult.Error -> {
                    emit(result)
                }
                is BusinessResult.Exception -> {
                    TODO()
                }
            }
        }
}