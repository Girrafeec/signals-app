package com.girrafeecstud.sos_signal_impl.data.datasource

import android.util.Log
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SosSignalDataSourceImpl @Inject constructor(

) : SosSignalDataSource {

    override fun sendSosSignal(
        sosSignal: SosSignal,
        userToken: String
    ): Flow<BusinessResult<Nothing>> = flow {
        Log.i("tag", "sos signal has been sent")
    }

    override fun updateSosSignal(
        sosSignal: SosSignal,
        userToken: String
    ): Flow<BusinessResult<Nothing>> {
        TODO("Not yet implemented")
    }

    override fun disableSosSignal(userToken: String): Flow<BusinessResult<Nothing>> {
        TODO("Not yet implemented")
    }
}