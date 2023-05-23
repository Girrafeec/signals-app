/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuers_impl.data.network

import com.girrafeecstud.signals.core_base.base.ListMapper
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import javax.inject.Inject

class RescuerDtoListMapper @Inject constructor(
    private val rescuerDtoMapper: RescuerDtoEntityMapper
) : ListMapper<RescuerDto, Rescuer> {

    override fun map(input: List<RescuerDto>): List<Rescuer> =
        input.map {
            rescuerDtoMapper.map(it)
        }
}