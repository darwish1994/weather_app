package com.currency.weatherapp.common.base

import androidx.lifecycle.ViewModel
import com.currency.weatherapp.common.network.helper.NetworkRemoteServiceCall
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel(), NetworkRemoteServiceCall {


}