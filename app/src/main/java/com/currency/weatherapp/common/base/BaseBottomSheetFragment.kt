package com.currency.weatherapp.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<VB : ViewBinding>() : BottomSheetDialogFragment() {
    private var _binding: VB? = null

     val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding( )
        return binding.root
    }

    abstract fun getViewBinding( ): VB?

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}