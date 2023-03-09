package com.girrafeecstud.signals.rescuers_impl.data.repository

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.rescuers_impl.data.datasource.RescuersDataSource
import com.girrafeecstud.signals.rescuers_impl.domain.RescuersRepository
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RescuersRepositoryImpl @Inject constructor(
    private val dataSource: RescuersDataSource
) : RescuersRepository {

    override fun getRescuersList(): Flow<BusinessResult<List<Rescuer>>> {
        val token = ""
        return dataSource.getRescuersList(token = token).flowOn(Dispatchers.IO)
    }
}