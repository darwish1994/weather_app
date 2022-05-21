package com.currency.weatherapp.data.di

import com.currency.weatherapp.common.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DaoModule {

    @Provides
    fun provideCityDao(database: WeatherDatabase) =  database.cityDoa()

}