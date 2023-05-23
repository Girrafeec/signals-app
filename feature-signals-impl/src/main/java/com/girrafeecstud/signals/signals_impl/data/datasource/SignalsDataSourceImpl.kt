package com.girrafeecstud.signals.signals_impl.data.datasource

import android.util.Log
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.SignalSender
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignalType
import com.girrafeecstud.signals.signals_impl.data.SignalsRandomizer
import com.girrafeecstud.signals.signals_impl.data.network.api.SosSignalsApi
import com.girrafeecstud.signals.signals_impl.data.network.mapper.EmergencySignalDtoMapper
import com.girrafeecstud.signals.signals_impl.data.network.mapper.EmergencySignalListDtoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class SignalsDataSourceImpl @Inject constructor(
    private val api: SosSignalsApi,
    private val listMapper: EmergencySignalListDtoMapper,
    private val mapper: EmergencySignalDtoMapper
) : SignalsDataSource {

    override suspend fun getSignalsList(token: String): Flow<BusinessResult<List<EmergencySignal>>> =
        flow {

            try {
                val response = api.getSignalsNear(authHeader = token)
                val responseBody = response.body()

                Log.i("tag sos signals list", "token $token")

                Log.i("tag sos signals list", "response code ${response.code()}")

                if(response.isSuccessful && responseBody != null) {
                    val signals = listMapper.map(input = responseBody)
                    Log.i("tag sos signals list", "$signals")
                    emit(BusinessResult.Success(_data = signals))
                }
            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }catch (exception: IOException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }

        }

    override suspend fun getSignalDetails(
        token: String,
        signalId: String
    ): Flow<BusinessResult<EmergencySignal>> =
        flow {
            try {
                val response = api.getSignalDetails(authHeader = token, signalId)
                val responseBody = response.body()

                Log.i("tag sos signals det", "response code ${response.code()}")

                if(response.isSuccessful && responseBody != null) {
                    val signal = mapper.map(responseBody)
                    Log.i("tag sos signals list", "$signal")
                    emit(BusinessResult.Success(_data = signal))
                }
            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }catch (exception: IOException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }
        }
}