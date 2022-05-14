package com.currency.weatherapp.common.repo

import com.currency.weatherapp.common.network.calls.WeatherApi
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getHistoricalWeather(q:String)=weatherApi.getHistorical(q=q)
}