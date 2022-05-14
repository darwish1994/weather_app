package com.currency.weatherapp.common.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivityMVVM<VB : ViewBinding, T : BaseViewModel> : BaseActivity<VB>() {

    private lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservables()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(getViewModelClass()) as T
    }

    protected fun getViewModel(): T {
        if (!::viewModel.isInitialized)
            initializeViewModel()
        return viewModel
    }

    protected abstract fun getViewModelClass(): Class<out BaseViewModel>

    protected abstract fun initObservables()
}