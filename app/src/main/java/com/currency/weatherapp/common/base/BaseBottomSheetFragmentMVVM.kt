package com.currency.weatherapp.common.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding

abstract class BaseBottomSheetFragmentMVVM<VB : ViewBinding, VM : BaseViewModel>() : BaseBottomSheetFragment<VB>() {
    protected lateinit var viewModel: Lazy<VM>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = initViewModel()
        onCreateInit()

    }



    abstract fun initViewModel(): Lazy<VM>

    abstract fun onCreateInit()

    fun getInitViewModel() = viewModel.value


}