package com.girrafeecstud.sos_signal_impl.domain.usecase

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_impl.domain.repository.SosSignalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class SendSosSignalUseCaseImpl @Inject constructor(
    private val repository: SosSignalRepository
) : SendSosSignalUseCase {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var _result: Flow<BusinessResult<Nothing>>? = null

    override val result: Flow<BusinessResult<Nothing>>
        get() = _result!!

    override fun invoke(sosSignal: SosSignal): Flow<BusinessResult<Nothing>> {
        _result = repository.sendSosSignal(sosSignal = sosSignal)
            .flowOn(Dispatchers.IO)
            .shareIn(scope = scope, started = SharingStarted.Lazily, replay = 1)
        return result
    }
}