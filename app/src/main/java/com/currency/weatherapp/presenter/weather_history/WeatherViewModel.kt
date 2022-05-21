package com.currency.weatherapp.presenter.weather_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.currency.weatherapp.common.base.BaseViewModel
import com.currency.weatherapp.common.network.helper.IViewState
import com.currency.weatherapp.data.remote.response.HistoricalResponse
import com.currency.weatherapp.data.repo.WeatherRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepoImpl: WeatherRepoImpl) :BaseViewModel() {

    private val weatherLiveData by lazy { MutableLiveData<IViewState<HistoricalResponse>>() }

    fun getHistoricalLiveData()=weatherLiveData

    fun getHistorical(q:String){
        viewModelScope.launch {
            safeApiCallGenericLiveData(weatherLiveData){
                weatherRepoImpl.getHistoricalWeather(q)
            }
        }
    }



}