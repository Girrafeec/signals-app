package com.girrafeecstud.signals.auth_impl.login.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.core_ui.extension.hideView
import com.girrafeecstud.core_ui.extension.removeView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.auth_impl.databinding.FragmentLoginBinding
import com.girrafeecstud.signals.auth_impl.login.presentation.LoginComponentViewModel
import com.girrafeecstud.signals.auth_impl.login.presentation.LoginUiState
import com.girrafeecstud.signals.auth_impl.login.presentation.LoginViewModel
import com.girrafeecstud.signals.auth_impl.ui.AuthFlowFragment
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val loginViewModel: LoginViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this)[LoginComponentViewModel::class.java]
            .loginComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObservers()
    }

    override fun setListeners() {
        binding.loginBtn.setOnClickListener { loginButtonClickListener() }
    }

    override fun registerObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.state
                    .onEach { state ->
                        setState(state)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }

    }

    override fun setState(state: UiState) {
        state as LoginUiState

        if (state.isLoading) {
            binding.progressBar.showView()
            binding.loginContainer.removeView()
        }
        else {
            binding.progressBar.removeView()
            binding.loginContainer.showView()
        }

        state.loginPassed?.let { loginPassed ->
            Log.i("login", "login passed? $loginPassed")
            if (loginPassed)
                (requireActivity() as ToFlowNavigable).navigateToScreen(
                    destination = FlowDestination.MapsFlow(_defaultScreen = DefaultMapsFlowScreen.SIGNALS_MAP_SCREEN)
                )
        }

        state.loginError?.let {
            if (it)
                Toast.makeText(parentFragment?.context, "error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginButtonClickListener() {
        loginViewModel.login(
            userPhoneNumber = binding.loginNameEdtTxt.text.toString(),
            userPassword = binding.loginPasswordEdtTxt.text.toString()
        )
    }

    private fun createAccountBtnListener() {
        (parentFragment?.parentFragment as AuthFlowFragment).openRegistrationFragment()
    }
}