package com.girrafeecstud.society_safety_app.feature_auth.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainState
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_auth.databinding.FragmentRegistrationBinding
import com.girrafeecstud.society_safety_app.feature_auth.presentation.RegistrationComponentViewModel
import com.girrafeecstud.society_safety_app.feature_auth.presentation.RegistrationViewModel
import javax.inject.Inject

class RegistrationFragment : BaseFragment() {

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

        subscribeObservers()
    }

    private fun registrationButtonListener() {
        registrationViewModel.registration(
            userPhoneNumber = binding.registrationPhoneNumberEdtTxt.text.toString(),
            userPassword = binding.registrationPasswordEdtTxt.text.toString(),
            userFirstName = binding.registrationFirstNameEdtTxt.text.toString(),
            userLastName = binding.registrationLastNameEdtTxt.text.toString()
        )
    }

    override fun subscribeObservers() {

        registrationViewModel.getState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is MainState.IsLoading -> handleLoading(isLoading = state.isLoading)
                is MainState.ErrorResult -> handleError(null)
                is MainState.SuccessResult -> handleSuccess(null)
            }
        }

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