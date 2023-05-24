package com.girrafeecstud.signals.rescuers_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngineState
import com.girrafeecstud.signals.rescuers_impl.di.RescuersFeatureComponent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RescuersService : Service() {

    @Inject
    lateinit var getRescuersListUseCase: IGetRescuersListUseCase

    private val binder = RescuersServiceBinder()

    private val rescuersServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    companion object {
        private var _rescuersEngineState: MutableStateFlow<RescuersEngineState> =
            MutableStateFlow(RescuersEngineState())
        val rescuersEngineState = _rescuersEngineState.asStateFlow()
    }

    override fun onCreate() {
        super.onCreate()
        RescuersFeatureComponent.rescuersFeatureComponent.inject(service = this)
    }

    override fun onDestroy() {
        rescuersServiceScope.cancel()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        getRescuers()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    //TODO create notification here with info about rescuers!!
    private fun getRescuers() {
        rescuersServiceScope.launch {
            getRescuersListUseCase()
                .onEach { result ->
                    when (result) {
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                        is BusinessResult.Success -> {
                            _rescuersEngineState.update {
                                it.copy(rescuers = result._data)
                            }
                            stopSelf()
                        }
                    }
                }
                .launchIn(rescuersServiceScope)
        }
    }

    private inner class RescuersServiceBinder : Binder() {
        fun getService() = this@RescuersService
    }
}