package com.currency.weatherapp.data.remote.calls

import com.currency.weatherapp.domain.model.City
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {


    @GET("search.json")
    suspend fun search(@Query("q") q:String):List<City>


}