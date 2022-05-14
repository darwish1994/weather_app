package com.currency.weatherapp.common.repo

import com.currency.weatherapp.common.database.CityDao
import com.currency.weatherapp.common.model.City
import com.currency.weatherapp.common.network.calls.CityApi
import javax.inject.Inject

class CityRepo @Inject constructor(
    private val cityApi: CityApi,
    private val cityDao: CityDao
) {

    suspend fun searchCity(q: String) = cityApi.search(q)

    fun getStoredCity() = cityDao.getAllCityFrmDb()

    suspend fun addCity(item:City)=cityDao.insertCity(item)

}