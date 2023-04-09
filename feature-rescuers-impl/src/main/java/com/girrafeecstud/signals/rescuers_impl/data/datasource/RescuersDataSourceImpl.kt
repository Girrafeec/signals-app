package com.girrafeecstud.signals.rescuers_impl.data.datasource

import android.util.Log
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuers_impl.data.RescuersRandomizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RescuersDataSourceImpl @Inject constructor(
    private val randomizer: RescuersRandomizer
) : RescuersDataSource {

    override fun getRescuersList(token: String): Flow<BusinessResult<List<Rescuer>>> =
        randomizer.getRescuers().map {
            delay(3000)
            if (it.isEmpty())
                BusinessResult.Success(_data = null)
            BusinessResult.Success(_data = it)
        }.flowOn(Dispatchers.IO)

    override fun getRescuerDetails(
        token: String,
        rescuerId: String
    ): Flow<BusinessResult<Rescuer>> =
        randomizer.getRescuerDetails(rescuerId = rescuerId)
            .flowOn(Dispatchers.IO)
}