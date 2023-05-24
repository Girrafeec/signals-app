/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.data

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface IRescuerModeDataSource {

    suspend fun acceptSosSignal(signalId: String, token: String): Flow<BusinessResult<EmptyResult>>

    suspend fun rejectSosSignal(signalId: String, token: String): Flow<BusinessResult<EmptyResult>>

}