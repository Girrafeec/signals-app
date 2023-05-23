/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuers_api.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface IGetRescuerDetailsUseCase {

    operator suspend fun invoke(rescuerId: String): Flow<BusinessResult<Rescuer>>

}