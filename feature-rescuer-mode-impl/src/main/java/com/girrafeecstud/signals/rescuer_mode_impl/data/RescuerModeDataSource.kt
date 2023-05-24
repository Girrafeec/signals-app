/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.data

import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.signals.rescuer_mode_impl.data.network.RescuersModeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class RescuerModeDataSource @Inject constructor(
    private val api: RescuersModeApi
) : IRescuerModeDataSource {

    override suspend fun acceptSosSignal(signalId: String, token: String):
            Flow<BusinessResult<EmptyResult>> =
        flow {
            try {
                val response = api.acceptSosSignal(authHeader = token, signalId = signalId)
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null)
                    emit(BusinessResult.Success(_data = EmptyResult))

            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }catch (exception: IOException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }
        }

    override suspend fun rejectSosSignal(signalId: String, token: String):
            Flow<BusinessResult<EmptyResult>> =
        flow {
            try {
                val response = api.rejectSosSignal(authHeader = token, signalId = signalId)
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null)
                    emit(BusinessResult.Success(_data = EmptyResult))

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