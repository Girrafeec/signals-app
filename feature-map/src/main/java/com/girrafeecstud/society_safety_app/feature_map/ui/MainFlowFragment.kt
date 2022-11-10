package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFlowFragment
import com.girrafeecstud.society_safety_app.feature_map.R
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentMainFlowBinding

class MainFlowFragment: BaseFlowFragment(
    R.id.nav_host_fragment_main_container
) {

    private var _binding: FragmentMainFlowBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // TODO добавить что-то?
    override fun setUpNavigation() {

    }

}