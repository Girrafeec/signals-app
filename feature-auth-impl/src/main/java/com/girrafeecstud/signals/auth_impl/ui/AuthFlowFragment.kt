package com.girrafeecstud.signals.auth_impl.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.signals.auth_impl.R
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkComponent
import com.girrafeecstud.signals.core_preferences.di.CorePreferencesComponent
import com.girrafeecstud.signals.auth_impl.databinding.FragmentAuthFlowBinding
import com.girrafeecstud.signals.auth_impl.di.AuthFeatureComponent

class AuthFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_auth_container
) {

    private var _binding: FragmentAuthFlowBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AuthFeatureComponent.authComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i("tag", "auth destroyed")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun openLoginFragment() {
        navController.navigate(R.id.action_global_login_fragment)
    }

    fun openRegistrationFragment() {
        navController.navigate(R.id.action_global_registration_fragment)
    }
}