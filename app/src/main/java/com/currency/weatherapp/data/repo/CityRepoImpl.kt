package com.currency.weatherapp.data.repo

import com.currency.weatherapp.common.database.CityDao
import com.currency.weatherapp.domain.model.City
import com.currency.weatherapp.data.remote.calls.CityApi
import com.currency.weatherapp.domain.repo.CityRepo
import javax.inject.Inject

class CityRepoImpl @Inject constructor(
    private val cityApi: CityApi,
    private val cityDao: CityDao
): CityRepo {

    override suspend fun searchCity(q: String) = cityApi.search(q)

    override fun getStoredCity() = cityDao.getAllCityFrmDb()

    override suspend fun addCity(item:City)=cityDao.insertCity(item)

}