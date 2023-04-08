/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.ui

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.girrafeecstud.signals.feature_signals_screens.databinding.SosTypeSelectedItemBinding
import com.girrafeecstud.signals.feature_signals_screens.databinding.SosTypeUnselectedItemBinding

class SosTypeViewHolder(
    private val binding: ViewBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bindUnselectedSosTypeItem(type: SosType, listener: SosTypeClickEvent?) {
        binding as SosTypeUnselectedItemBinding

        val lightGrayColor = ContextCompat.getColor(context, com.girrafeecstud.core_ui.R.color.light_gray_disabled)

        binding.sosTypeName.setTextColor(lightGrayColor)
        binding.sosTypeImage.colorFilter = PorterDuffColorFilter(lightGrayColor, PorterDuff.Mode.SRC_IN)
        binding.sosTypeImage.setImageResource(type.typeImageResId)
        binding.sosTypeName.text = type.typeName
        binding.sosTypeItem.setOnClickListener {
            if (!type.isSelected)
                listener?.onSosTypeSelected(type = type)
        }
    }

    fun bindSelectedSosTypeItem(type: SosType, listener: SosTypeClickEvent?) {
        binding as SosTypeSelectedItemBinding

        val redColor = ContextCompat.getColor(context, com.girrafeecstud.core_ui.R.color.sos_red)

        binding.sosTypeName.setTextColor(redColor)
        binding.sosTypeImage.colorFilter = PorterDuffColorFilter(redColor, PorterDuff.Mode.SRC_IN)
        binding.sosTypeImage.setImageResource(type.typeImageResId)
        binding.sosTypeName.text = type.typeName
        binding.sosTypeItem.setOnClickListener {
            if (!type.isSelected)
                listener?.onSosTypeSelected(type = type)
        }
    }

}