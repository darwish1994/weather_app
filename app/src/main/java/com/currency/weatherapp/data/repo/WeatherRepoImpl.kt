package com.currency.weatherapp.data.repo

import com.currency.weatherapp.data.remote.calls.WeatherApi
import com.currency.weatherapp.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(private val weatherApi: WeatherApi): WeatherRepo {

     override suspend fun getHistoricalWeather(q:String)=weatherApi.getHistorical(q=q)
}