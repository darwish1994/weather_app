package com.currency.weatherapp.common.network.response

import com.google.gson.annotations.SerializedName

open class ResponseWrapper<T>(val message: String, @SerializedName("data") val data: T, val status: Boolean)