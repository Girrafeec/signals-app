package com.girrafeecstud.signals.rescuers_api.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface GetRescuersListUseCase {

    operator fun invoke(): Flow<BusinessResult<List<Rescuer>>>

}