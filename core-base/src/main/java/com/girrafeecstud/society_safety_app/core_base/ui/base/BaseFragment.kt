package com.girrafeecstud.society_safety_app.core_base.ui.base

import androidx.fragment.app.Fragment

// TODO добавлять ли сюда зависимость от фрагмента?
abstract class BaseFragment: Fragment() {

    protected open fun registerObservers() {}

    protected open fun handleLoading(isLoading: Boolean) {}

    protected open fun handleSuccess(any: Any?) {}

    protected open fun handleError(any: Any?) {}

    protected open fun setListeners() {}

}