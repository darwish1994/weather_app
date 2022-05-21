package com.currency.weatherapp.domain.usecase

import com.currency.weatherapp.domain.model.City
import com.currency.weatherapp.domain.repo.CityRepo
import javax.inject.Inject

class AddCityUseCase @Inject constructor(private val cityRepo: CityRepo, ) {

    suspend operator fun invoke(item: City) = cityRepo.addCity(item)

}