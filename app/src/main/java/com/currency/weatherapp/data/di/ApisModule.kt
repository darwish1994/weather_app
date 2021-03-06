package com.currency.weatherapp.data.di

import com.currency.weatherapp.data.remote.calls.CityApi
import com.currency.weatherapp.data.remote.calls.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ApisModule {

    @Provides
    fun provideCityApiCalls(retrofit: Retrofit): CityApi {
        return retrofit.create(CityApi::class.java)
    }
    @Provides
    fun provideWeatherApiCalls(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

}