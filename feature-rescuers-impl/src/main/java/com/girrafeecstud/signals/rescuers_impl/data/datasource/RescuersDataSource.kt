package com.girrafeecstud.signals.rescuers_impl.data.datasource

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface RescuersDataSource {

    fun getRescuersList(token: String): Flow<BusinessResult<List<Rescuer>>>

}