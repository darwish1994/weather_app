package com.currency.weatherapp.domain.usecase

import com.currency.weatherapp.domain.repo.CityRepo
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val cityRepo: CityRepo, ) {

     operator fun invoke() = cityRepo.getStoredCity()

}