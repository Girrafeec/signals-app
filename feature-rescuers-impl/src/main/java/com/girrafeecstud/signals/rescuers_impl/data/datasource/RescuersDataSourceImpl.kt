package com.girrafeecstud.signals.rescuers_impl.data.datasource

import android.util.Log
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuers_impl.data.RescuersRandomizer
import com.girrafeecstud.signals.rescuers_impl.data.network.RescuerDtoEntityMapper
import com.girrafeecstud.signals.rescuers_impl.data.network.RescuerDtoListMapper
import com.girrafeecstud.signals.rescuers_impl.data.network.RescuersApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class RescuersDataSourceImpl @Inject constructor(
    private val rescuerDtoMapper: RescuerDtoEntityMapper,
    private val rescuerDtoListMapper: RescuerDtoListMapper,
    private val api: RescuersApi
) : RescuersDataSource {

    override suspend fun getRescuersList(token: String): Flow<BusinessResult<List<Rescuer>>> =
        flow {
            try {

                val response = api.getRescuersList(authHeader = token)
                val responseBody = response.body()

                Log.i("tag rescuers list", "token $token")

                Log.i("tag rescuers list", "response code ${response.code()}")

                if(response.isSuccessful && responseBody != null) {
                    val rescuers = rescuerDtoListMapper.map(responseBody)
                    Log.i("tag rescuers list", "$rescuers")
                    emit(BusinessResult.Success(_data = rescuers))
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
        }.flowOn(Dispatchers.IO)

    override suspend fun getRescuerDetails(
        token: String,
        rescuerId: String
    ): Flow<BusinessResult<Rescuer>> =
        flow {
            try {
                val response = api.getRescuerDetails(authHeader = token, rescuerId)
                val responseBody = response.body()

                Log.i("tag rescuer details", "token $token")

                Log.i("tag rescuer details", "response code ${response.code()}")

                if(response.isSuccessful && responseBody != null) {
                    val rescuer = rescuerDtoMapper.map(responseBody)
                    Log.i("tag rescuer details", "$rescuer")
                    emit(BusinessResult.Success(_data = rescuer))
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
        }.flowOn(Dispatchers.IO)
}