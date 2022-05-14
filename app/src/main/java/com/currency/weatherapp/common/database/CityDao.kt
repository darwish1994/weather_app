package com.currency.weatherapp.common.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currency.weatherapp.common.model.City

@Dao
interface CityDao {


    @Query("SELECT * FROM City")
    fun getAllCityFrmDb(): LiveData<List<City>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)


}