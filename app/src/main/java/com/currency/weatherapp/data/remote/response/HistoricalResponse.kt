package com.currency.weatherapp.data.remote.response

import com.currency.weatherapp.domain.model.City
import com.currency.weatherapp.domain.model.Forecast
import com.google.gson.annotations.SerializedName

data class HistoricalResponse (
    @SerializedName("location")
    val city:City,
    @SerializedName("forecast")
    val forecastday: Forecast

)