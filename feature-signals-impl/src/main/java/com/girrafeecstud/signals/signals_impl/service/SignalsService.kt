package com.girrafeecstud.signals.signals_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.GetSignalsListUseCase
import com.girrafeecstud.signals.signals_api.engine.SignalsEngineState
import com.girrafeecstud.signals.signals_impl.di.SignalsFeatureComponent
import com.girrafeecstud.signals.signals_impl.domain.GetSignalDetailsUseCase
import com.girrafeecstud.signals.signals_impl.utils.SignalsFeatureImplUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SignalsService : Service() {

    @Inject
    lateinit var getSignalsListUseCase: GetSignalsListUseCase

    @Inject
    lateinit var getSignalDetailsUseCase: GetSignalDetailsUseCase

    private val binder = SignalsServiceBinder()

    private val signalsServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    companion object {
        private val _signalsEngineState: MutableStateFlow<SignalsEngineState> =
            MutableStateFlow(SignalsEngineState())
        var signalsEngineState = _signalsEngineState.asStateFlow()
    }

    override fun onCreate() {
        super.onCreate()
        SignalsFeatureComponent.signalsFeatureComponent.inject(service = this)
    }

    override fun onDestroy() {
        signalsServiceScope.cancel()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val signalId = it.getStringExtra("signalId")
            when (it.action) {
                SignalsFeatureImplUtils.ACTION_GET_SIGNALS -> {
                    getSignals()
                }
                SignalsFeatureImplUtils.ACTION_GET_SIGNAL_DETAILS -> {
                    if (signalId != null) {
                        getSignalDetails(signalId = signalId)
                    }
                }
            }
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private fun getSignals() {
        signalsServiceScope.launch {
            getSignalsListUseCase()
                .onEach { result ->
                    when (result) {
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                        is BusinessResult.Success -> {
                            _signalsEngineState.update {
                                it.copy(signals = result._data)
                            }
                            Log.i("tag", "got signals")
                            stopSelf()
                        }
                    }
                }
                .launchIn(signalsServiceScope)
        }
    }

    private fun getSignalDetails(signalId: String) {
        signalsServiceScope.launch {
            getSignalDetailsUseCase(signalId = signalId)
                .onEach { result ->
                when (result) {
                    is BusinessResult.Error -> {}
                    is BusinessResult.Exception -> {}
                    is BusinessResult.Success -> {
                        _signalsEngineState.update {
                            it.copy(signals = listOf(result._data!!))
                        }
                        stopSelf()
                    }
                }
            }
            .launchIn(signalsServiceScope)

        }
    }

    private inner class SignalsServiceBinder : Binder() {
        fun getService() = this@SignalsService
    }
}