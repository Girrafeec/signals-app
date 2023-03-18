package com.girrafeecstud.signals.rescuers_impl.domain

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface RescuersRepository {

    fun getRescuersList(): Flow<BusinessResult<List<Rescuer>>>

}