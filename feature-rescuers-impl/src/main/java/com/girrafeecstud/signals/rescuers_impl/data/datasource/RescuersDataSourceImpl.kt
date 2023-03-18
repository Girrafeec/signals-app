package com.girrafeecstud.signals.rescuers_impl.data.datasource

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.delay
import javax.inject.Inject

class RescuersDataSourceImpl @Inject constructor(

) : RescuersDataSource {

    private val rescuersTempList = listOf(
        Rescuer(
            rescuerId = "ce0c4cf7-9968-4155-bea2-e310b1248b08",
            rescuerFirstName = "Mike",
            rescuerLastName = "Wazowski",
            rescuerPhoneNumber = "+79234556985",
            rescuerProfileImageUrl = "https://static.wikia.nocookie.net/pixar/images/3/38/Mike1.png/revision/latest?cb=20210509121400",
            rescuerLocationLatitude = 60.030479,
            rescuerLocationLongitude = 30.633914
        ),
        Rescuer(
            rescuerId = "16866392-53b1-4398-8a11-5cadfb559e7d",
            rescuerFirstName = "Randall",
            rescuerLastName = "Boggs",
            rescuerPhoneNumber = "+79999999999",
            rescuerProfileImageUrl = "https://static.wikia.nocookie.net/pixar/images/8/86/Randall.png/revision/latest/scale-to-width-down/1000?cb=20160404023111",
            rescuerLocationLatitude = 60.029972,
            rescuerLocationLongitude = 30.631602
        ),
        Rescuer(
            rescuerId = "843c7665-5171-4e0c-b976-d6220e1dfbd3",
            rescuerFirstName = "Celia",
            rescuerLastName = "Mae",
            rescuerPhoneNumber = "+79435586985",
            rescuerProfileImageUrl = "https://static.wikia.nocookie.net/disney/images/9/96/Profile_-_Celia.png/revision/latest?cb=20190313100849",
            rescuerLocationLatitude = 60.029731,
            rescuerLocationLongitude = 30.634857
        )
    )

    override fun getRescuersList(token: String): Flow<BusinessResult<List<Rescuer>>> =
        flow {
            delay(3000)
            emit(BusinessResult.Success(_data = rescuersTempList))
        }.flowOn(Dispatchers.IO)

}