package com.girrafeecstud.core_ui.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.girrafeecstud.core_ui.presentation.UiState

// TODO добавлять ли сюда зависимость от фрагмента?
abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        registerObservers()
    }

    protected open fun setListeners() {}

    protected open fun registerObservers() {}

    protected open fun handleLoading(isLoading: Boolean) {}

    protected open fun handleSuccess(any: Any?) {}

    protected open fun handleError(any: Any?) {}

    protected open fun setState(state: UiState) {}

}