package com.girrafeecstud.society_safety_app.feature_auth.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFlowFragment
import com.girrafeecstud.society_safety_app.feature_auth.R
import com.girrafeecstud.society_safety_app.feature_auth.databinding.FragmentAuthFlowBinding
import com.girrafeecstud.society_safety_app.feature_auth.di.AuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.presentation.AuthComponentViewModel

class AuthFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_auth_container
) {

    private var _binding: FragmentAuthFlowBinding? = null

    private val binding get() = _binding!!

    private var _authComponent: AuthComponent? = null

    val authComponent get() = _authComponent!!

    override fun onAttach(context: Context) {
        _authComponent = ViewModelProvider(this).get(AuthComponentViewModel::class.java).authComponent
        authComponent.inject(this)
//        ViewModelProvider(this).get(AuthComponentViewModel::class.java)
//            .authComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthFlowBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
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