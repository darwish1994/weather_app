package com.currency.weatherapp.data.remote.calls

import com.currency.weatherapp.data.remote.response.HistoricalResponse
import com.currency.weatherapp.common.util.formatToServerDateDefaults
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface WeatherApi {


    @GET("history.json")
    suspend fun getHistorical(@Query("q") q: String, @Query("dt") dt: String=Date().formatToServerDateDefaults()): HistoricalResponse


}