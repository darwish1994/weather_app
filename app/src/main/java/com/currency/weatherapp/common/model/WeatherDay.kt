package com.currency.weatherapp.common.model


import com.google.gson.annotations.SerializedName

data class WeatherDay(
    @SerializedName("avghumidity")
    val avghumidity: Double,
    @SerializedName("avgtemp_c")
    val avgtempC: Double,
    @SerializedName("avgvis_km")
    val avgvisKm: Double,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("maxwind_kph")
    val maxwindKph: Double,
    @SerializedName("totalprecip_in")
    val totalprecipIn: Double
)