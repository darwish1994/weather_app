package com.currency.weatherapp.data.di

import com.currency.weatherapp.common.database.CityDao
import com.currency.weatherapp.data.remote.calls.CityApi
import com.currency.weatherapp.data.remote.calls.WeatherApi
import com.currency.weatherapp.data.repo.CityRepoImpl
import com.currency.weatherapp.data.repo.WeatherRepoImpl
import com.currency.weatherapp.domain.repo.CityRepo
import com.currency.weatherapp.domain.repo.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepoModule {

    @Provides
    fun provideCityRepo(
        cityApi: CityApi,
        cityDao: CityDao
    ): CityRepo = CityRepoImpl(cityApi, cityDao)


    @Provides
    fun provideWeatherRepo(weatherApi: WeatherApi): WeatherRepo =WeatherRepoImpl(weatherApi)

}