package com.currency.weatherapp.domain.model


import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val weatherDay: WeatherDay,
    @SerializedName("hour")
    val weatherHour: List<WeatherHour>
)