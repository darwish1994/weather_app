package com.currency.weatherapp.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherHour(
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("wind_kph")
    val wind: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("time_epoch")
    val timeEpoch: Long
): Parcelable