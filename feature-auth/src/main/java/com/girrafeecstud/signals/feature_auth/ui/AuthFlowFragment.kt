package com.girrafeecstud.signals.feature_auth.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.girrafeecstud.signals.core_base.ui.base.BaseFlowFragment
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkComponent
import com.girrafeecstud.signals.core_preferences.di.CorePreferencesComponent
import com.girrafeecstud.signals.feature_auth.R
import com.girrafeecstud.signals.feature_auth.databinding.FragmentAuthFlowBinding
import com.girrafeecstud.signals.feature_auth.di.AuthComponent
import com.girrafeecstud.signals.feature_auth.di.DaggerAuthComponent_AuthDependenciesComponent

class AuthFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_auth_container
) {

    private var _binding: FragmentAuthFlowBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {

        AuthComponent.init(
            DaggerAuthComponent_AuthDependenciesComponent
                .builder()
                .coreNetworkApi(CoreNetworkComponent.coreNetworkComponent)
                .corePreferencesApi(CorePreferencesComponent.corePreferencesComponent)
//                .coreBaseApi(CoreBaseComponent.coreBaseComponent)
                .build()
        )

        AuthComponent.authComponent.inject(this)
        super.onAttach(context)
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
        AuthComponent.reset()
        Log.i("tag", "auth destroyed")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // TODO добавить что-то?
    override fun setUpNavigation() {

    }

    // TODO как открыть новый flow fragment, если я не знаю его id и id из графа навигации в mainComponent
    fun openMainFlowFragment() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.girrafeecstud.society_safety_app/main_flow_fragment".toUri())
            .build()
        //navController.navigate(request)
        findNavController().navigate(request)
    }

    fun openLoginFragment() {
        navController.navigate(R.id.action_global_login_fragment)
    }

    fun openRegistrationFragment() {
        navController.navigate(R.id.action_global_registration_fragment)
    }
}