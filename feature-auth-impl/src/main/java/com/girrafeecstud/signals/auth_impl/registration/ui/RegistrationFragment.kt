package com.girrafeecstud.signals.auth_impl.registration.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.auth_impl.databinding.FragmentRegistrationBinding
import com.girrafeecstud.signals.auth_impl.registration.presentation.RegistrationComponentViewModel
import com.girrafeecstud.signals.auth_impl.registration.presentation.RegistrationViewModel
import com.girrafeecstud.signals.auth_impl.ui.AuthFlowFragment
import javax.inject.Inject

class RegistrationFragment : com.girrafeecstud.core_ui.ui.BaseFragment() {

    private var _binding: FragmentRegistrationBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val registrationViewModel: RegistrationViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get(RegistrationComponentViewModel::class.java)
            .registrationComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registrationBtn.setOnClickListener { registrationButtonListener() }
        binding.openLoginFragmentBtn.setOnClickListener { signUpButtonListener() }

        registerObservers()
    }

    private fun registrationButtonListener() {
//        registrationViewModel.registration(
//            userPhoneNumber = binding.registrationPhoneNumberEdtTxt.text.toString(),
//            userPassword = binding.registrationPasswordEdtTxt.text.toString(),
//            userFirstName = binding.registrationFirstNameEdtTxt.text.toString(),
//            userLastName = binding.registrationLastNameEdtTxt.text.toString()
//        )
    }

    override fun registerObservers() {

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                registrationViewModel.state
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
        Log.i("tag", "log")
        (parentFragment?.parentFragment as AuthFlowFragment).openLoginFragment()
    }

    override fun handleError(any: Any?) {
        // TODO поменять на интерфейсы
        Toast.makeText(parentFragment?.context, "error", Toast.LENGTH_SHORT).show()
    }

    private fun signUpButtonListener() {
        (parentFragment?.parentFragment as AuthFlowFragment).openLoginFragment()
    }
}