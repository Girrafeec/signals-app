package com.girrafeecstud.society_safety_app.feature_map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentMapBinding

class MapFragment : BaseFragment() {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}