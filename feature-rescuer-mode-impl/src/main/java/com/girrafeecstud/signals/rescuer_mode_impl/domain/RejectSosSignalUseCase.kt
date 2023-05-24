/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RejectSosSignalUseCase @Inject constructor(
    private val repository: IRescuerModeRepository
) {

    suspend operator fun invoke(signalId: String): Flow<BusinessResult<EmptyResult>> =
        repository.rejectSosSignal(signalId)

}