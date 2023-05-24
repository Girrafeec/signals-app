/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface IRescuerModeRepository {

    suspend fun acceptSosSignal(signalId: String): Flow<BusinessResult<EmptyResult>>

    suspend fun rejectSosSignal(signalId: String): Flow<BusinessResult<EmptyResult>>

}