package com.currency.weatherapp.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.currency.weatherapp.common.model.City

@Database(
    entities = [City::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun cityDoa(): CityDao
}