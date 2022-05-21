package com.currency.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecast: List<ForecastDay>
    )
