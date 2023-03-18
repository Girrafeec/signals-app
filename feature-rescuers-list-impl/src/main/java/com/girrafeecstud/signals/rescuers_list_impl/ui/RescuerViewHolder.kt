package com.girrafeecstud.signals.rescuers_list_impl.ui

import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.rescuers_list_impl.databinding.RescuerItemBinding
import com.girrafeecstud.signals.core_base.ui.extension.loadAndSetImage

class RescuerViewHolder(
    private val binding: RescuerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    private fun bindRescuerItem(rescuer: Rescuer, listener: RescuerClickEvent?) {
        binding.rescuerProfileImage.loadAndSetImage(rescuer.rescuerProfileImageUrl)
        binding.rescuerItem.setOnClickListener{ listener?.onRescuerClick(rescuer = rescuer) }
    }

    fun bind(rescuer: Rescuer, listener: RescuerClickEvent?) {
        bindRescuerItem(rescuer = rescuer, listener = listener)
    }

}