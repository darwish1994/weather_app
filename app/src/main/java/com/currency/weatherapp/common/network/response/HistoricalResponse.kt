package com.currency.weatherapp.common.network.response

import com.currency.weatherapp.common.model.City
import com.currency.weatherapp.common.model.Forecast
import com.google.gson.annotations.SerializedName

data class HistoricalResponse (
    @SerializedName("location")
    val city:City,
    @SerializedName("forecast")
    val forecastday: Forecast

)