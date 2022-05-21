package com.currency.weatherapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("errors")
    val errors: Map<String, List<String>>? = null
)