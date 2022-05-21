package com.currency.weatherapp.domain.repo

import androidx.lifecycle.LiveData
import com.currency.weatherapp.domain.model.City

interface CityRepo {

    suspend fun searchCity(q: String): List<City>

    suspend fun addCity(item:City)

    fun getStoredCity() : LiveData<List<City>>

}