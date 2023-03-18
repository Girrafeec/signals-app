package com.girrafeecstud.signals.rescuers_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.girrafeecstud.signals.rescuers_api.domain.GetRescuersListUseCase
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RescuersService : Service() {

    @Inject
    lateinit var getRescuersListUseCase: GetRescuersListUseCase

    private val binder = RescuersServiceBinder()

    private val rescuersServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
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
        getRescuersListUseCase()
            .onEach { result ->
                when (result) {
                    is BusinessResult.Error -> {}
                    is BusinessResult.Exception -> {}
                    is BusinessResult.Success -> {
                        stopSelf()
                    }
                }
            }
            .launchIn(rescuersServiceScope)
    }

    private inner class RescuersServiceBinder : Binder() {
        fun getService() = this@RescuersService
    }
}