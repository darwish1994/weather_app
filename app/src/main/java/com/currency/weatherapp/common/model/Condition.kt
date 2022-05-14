package com.currency.weatherapp.common.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Condition(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
):Parcelable