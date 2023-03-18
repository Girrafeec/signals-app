package com.girrafeecstud.signals.feature_auth.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.core_base.ui.base.BaseFragment
import com.girrafeecstud.signals.feature_auth.databinding.FragmentLoginBinding
import com.girrafeecstud.signals.feature_auth.presentation.LoginComponentViewModel
import com.girrafeecstud.signals.feature_auth.presentation.LoginViewModel
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
        ViewModelProvider(this).get(LoginComponentViewModel::class.java)
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

        binding.loginBtn.setOnClickListener { loginButtonClickListener() }
        binding.createAccountBtn.setOnClickListener { createAccountBtnListener() }

        registerObservers()
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

    override fun registerObservers() {

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                loginViewModel.state
//                    .onEach { state ->
//                        when (state) {
//                            is UiState.Loading -> handleLoading(isLoading = true)
//                            is UiState.Error -> handleError(null)
//                            is UiState.Success -> handleSuccess(null)
//                        }
//                    }
//                    .launchIn(lifecycleScope)
//            }
//        }

    }

    override fun handleLoading(isLoading: Boolean) {

    }

    override fun handleSuccess(any: Any?) {
        Log.i("tag", "login success")
        (parentFragment?.parentFragment as AuthFlowFragment).openMainFlowFragment()
    }

    override fun handleError(any: Any?) {
        Toast.makeText(parentFragment?.context, "error", Toast.LENGTH_SHORT).show()
    }
}