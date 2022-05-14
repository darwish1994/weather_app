package com.currency.weatherapp.common.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecast: List<ForecastDay>
    )
