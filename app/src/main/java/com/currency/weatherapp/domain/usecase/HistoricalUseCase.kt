package com.currency.weatherapp.domain.usecase

import com.currency.weatherapp.common.network.helper.NetworkRemoteServiceCall
import com.currency.weatherapp.common.network.helper.NetworkResponse
import com.currency.weatherapp.data.remote.response.HistoricalResponse
import com.currency.weatherapp.domain.model.City
import com.currency.weatherapp.domain.repo.CityRepo
import com.currency.weatherapp.domain.repo.WeatherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoricalUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
): NetworkRemoteServiceCall {

    operator fun invoke(q:String):Flow<NetworkResponse<HistoricalResponse>> = flow {
        emit(NetworkResponse.loading())
        emit(safeApiCallWithoutBaseResponse2 {
            weatherRepo.getHistoricalWeather(q)
        })
    }



}