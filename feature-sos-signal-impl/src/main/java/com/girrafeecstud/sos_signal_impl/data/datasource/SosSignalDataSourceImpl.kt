package com.girrafeecstud.sos_signal_impl.data.datasource

import android.util.Log
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_impl.data.network.SosSignalDtoMapper
import com.girrafeecstud.sos_signal_impl.data.network.SosSignalsApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class SosSignalDataSourceImpl @Inject constructor(
    private val api: SosSignalsApi,
    private val mapper: SosSignalDtoMapper
) : SosSignalDataSource {

    override suspend fun sendSosSignal(
        sosSignal: SosSignal,
        userToken: String
    ): Flow<BusinessResult<EmptyResult>> = flow {

        val requestBody = mapper.map(sosSignal)

        try {
            Log.i("tag sos", "request body $requestBody")
            Log.i("tag sos", "request auth $userToken")
            val response = api.sendSosSignal(authHeader = userToken, body = requestBody)
            val responseBody  = response.body()

            Log.i("tag sos signal", "response code ${response.code()}")

            if (response.isSuccessful && responseBody != null) {
                Log.i("tag", "sos signal has been sent")
                emit(BusinessResult.Success(_data = EmptyResult))
            }

        } catch (exception: NoNetworkConnectionException) {
            emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
        } catch (exception: SocketTimeoutException) {
            emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
        }catch (exception: IOException) {
            exception.printStackTrace()
            emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
        }
    }

    override suspend fun updateSosSignal(
        sosSignal: SosSignal,
        userToken: String
    ): Flow<BusinessResult<EmptyResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun disableSosSignal(userToken: String): Flow<BusinessResult<EmptyResult>> =
        flow {

            try {
                val response = api.disableSosSignal(authHeader = userToken)
                val responseBody  = response.body()

                Log.i("tag sos signal", "response code ${response.code()}")

                if (response.isSuccessful && responseBody != null) {
                    Log.i("tag", "sos signal has been disabled")
                    emit(BusinessResult.Success(_data = EmptyResult))
                }

            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }catch (exception: IOException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }
        }
}