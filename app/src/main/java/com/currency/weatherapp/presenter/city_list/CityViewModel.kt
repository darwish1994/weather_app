package com.currency.weatherapp.presenter.city_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.currency.weatherapp.common.base.BaseViewModel
import com.currency.weatherapp.common.network.helper.IViewState
import com.currency.weatherapp.domain.model.City
import com.currency.weatherapp.domain.usecase.AddCityUseCase
import com.currency.weatherapp.domain.usecase.GetCitiesUseCase
import com.currency.weatherapp.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val searchCityUseCase: SearchCityUseCase,
    private val addCityUseCase: AddCityUseCase


) : BaseViewModel(){


    private val citesLiveData by lazy { getCitiesUseCase.invoke() }

    private val searchLiveData by lazy { MutableLiveData<IViewState<List<City>>>() }

    private var job: Job? = null

    fun getSearchObserver() = searchLiveData
    fun getCityObserver() = citesLiveData

    fun searchCity(q: String) {
        job?.cancel()
        job = searchCityUseCase(q).onEach {
            delay(500)
            searchLiveData.value = it
        }.launchIn(viewModelScope)

//        launch {
//            safeApiCallGenericLiveData(searchLiveData) {
//                cityRepoImpl.searchCity(q)
//            }
//        }
    }

    fun addCity(item: City) {

        viewModelScope.launch(Dispatchers.IO) {
            addCityUseCase.invoke(item)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}