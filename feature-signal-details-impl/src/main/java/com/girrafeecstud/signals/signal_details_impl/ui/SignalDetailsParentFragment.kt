package com.girrafeecstud.signals.signal_details_impl.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.girrafeecstud.signals.signal_details_api.ui.ISignalDetailsFragment
import com.girrafeecstud.signals.signal_details_impl.R
import com.girrafeecstud.signals.signal_details_impl.databinding.FragmentParentSignalDetailsBinding
import com.girrafeecstud.signals.signal_details_impl.databinding.FragmentSignalDetailsBinding
import com.girrafeecstud.signals.signal_details_impl.di.SignalDetailsFeatureComponent
import javax.inject.Inject

class SignalDetailsParentFragment @Inject constructor(

) : ISignalDetailsFragment() {

    private var _binding: FragmentParentSignalDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        SignalDetailsFeatureComponent.signalDetailsFeatureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentParentSignalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}