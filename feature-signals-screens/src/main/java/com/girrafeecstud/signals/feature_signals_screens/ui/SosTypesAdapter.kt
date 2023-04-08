/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.signals.feature_signals_screens.databinding.SosTypeSelectedItemBinding
import com.girrafeecstud.signals.feature_signals_screens.databinding.SosTypeUnselectedItemBinding
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType
import javax.inject.Inject

class SosTypesAdapter @Inject constructor(

) : RecyclerView.Adapter<SosTypeViewHolder>() {

    var listener: SosTypeClickEvent? = null

    private val sosTypes = listOf(
        SosType(
            typeId = 0,
            typeImageResId = com.girrafeecstud.core_ui.R.drawable.ic_bell_red_big,
            typeName = "Стандартный",
            isSelected = true,
            type = SosSignalType.DEFAULT_SOS_SIGNAL
        ),
        SosType(
            typeId = 1,
            typeImageResId = com.girrafeecstud.core_ui.R.drawable.ic_heart_red,
            typeName = "Инфаркт",
            isSelected = false,
            type = SosSignalType.HEART_ATTACK_SIGNAL
        )
    )

    var selectedType = sosTypes.get(0)

    private var selectedPosition: Int = 0

    private sealed class SosTypeViewType(
        val typeId: Int
    ) {
        object SelectedSosType : SosTypeViewType(typeId = 0)
        object UnselectedSosType : SosTypeViewType(typeId = 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SosTypeViewHolder {
        val binding = if (viewType == SosTypeViewType.SelectedSosType.typeId) {
            SosTypeSelectedItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        }
        else {
            SosTypeUnselectedItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return SosTypeViewHolder(binding = binding, context = parent.context)
    }

    override fun onBindViewHolder(holder: SosTypeViewHolder, position: Int) {
        val isSelected = sosTypes[position].isSelected
        if (isSelected)
            holder.bindSelectedSosTypeItem(type = sosTypes[position], listener = listener)
        else
            holder.bindUnselectedSosTypeItem(type = sosTypes[position], listener = listener)
    }

    override fun getItemCount(): Int {
        return sosTypes.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (sosTypes[position].isSelected)
            SosTypeViewType.SelectedSosType.typeId
        else
            SosTypeViewType.UnselectedSosType.typeId
    }

    fun setSelectedSosType(typeId: Int) {
        val previousSelectedPosition = selectedPosition
        for (i in sosTypes.indices) {
            if (sosTypes[i].typeId == typeId && !sosTypes[i].isSelected) {
                sosTypes[i].isSelected = true
                selectedType = sosTypes[i]
            }
            else {
                sosTypes[i].isSelected = false
            }
        }
        notifyDataSetChanged()
    }
}