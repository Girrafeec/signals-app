package com.girrafeecstud.signals.rescuers_list_impl.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.rescuers_list_impl.databinding.RescuerItemBinding
import javax.inject.Inject

class RescuersAdapter @Inject constructor(

) : RecyclerView.Adapter<RescuerViewHolder>() {

    var listener: RescuerClickEvent? = null

    private var rescuers = ArrayList<Rescuer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RescuerViewHolder {
        val binding = RescuerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RescuerViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: RescuerViewHolder, position: Int) {
        holder.bind(rescuer = rescuers.get(position), listener = listener)
    }

    override fun getItemCount(): Int {
        return rescuers.size
    }

    fun refreshRescuersList(rescuers: List<Rescuer>) {
        this.rescuers = ArrayList(rescuers)
        notifyDataSetChanged()
    }
}