package com.currency.weatherapp.domain.repo

import com.currency.weatherapp.data.remote.response.HistoricalResponse

interface WeatherRepo {

    suspend fun getHistoricalWeather(q:String) : HistoricalResponse
}