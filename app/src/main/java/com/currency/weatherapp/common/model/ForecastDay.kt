package com.currency.weatherapp.common.model


import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val weatherDay: WeatherDay,
    @SerializedName("hour")
    val weatherHour: List<WeatherHour>
)