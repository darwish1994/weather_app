package com.currency.weatherapp.domain.usecase

import com.currency.weatherapp.common.network.helper.NetworkRemoteServiceCall
import com.currency.weatherapp.common.network.helper.NetworkResponse
import com.currency.weatherapp.domain.model.City
import com.currency.weatherapp.domain.repo.CityRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val cityRepoImpl: CityRepo
): NetworkRemoteServiceCall {

    operator fun invoke(q:String):Flow<NetworkResponse<List<City>>> = flow {
        emit(NetworkResponse.loading())
        emit(safeApiCallWithoutBaseResponse2 {
            cityRepoImpl.searchCity(q)
        })
    }



}