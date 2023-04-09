package com.girrafeecstud.signals.rescuers_api.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface IGetRescuersListUseCase {

    operator fun invoke(): Flow<BusinessResult<List<Rescuer>>>

}