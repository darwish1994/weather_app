package com.currency.weatherapp.data.remote.response

import com.google.gson.annotations.SerializedName

open class ResponseWrapper<T>(val message: String, @SerializedName("data") val data: T, val status: Boolean)