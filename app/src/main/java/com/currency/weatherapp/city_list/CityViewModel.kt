package com.currency.weatherapp.city_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.currency.weatherapp.common.base.BaseViewModel
import com.currency.weatherapp.common.model.City
import com.currency.weatherapp.common.network.helper.IViewState
import com.currency.weatherapp.common.repo.CityRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CityViewModel @Inject constructor(private val cityRepo: CityRepo) : BaseViewModel(),
    CoroutineScope {

    private val citesLiveData by lazy { cityRepo.getStoredCity() }

    private val searchLiveData by lazy { MutableLiveData<IViewState<List<City>>>() }

    private val job: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getSearchObserver() = searchLiveData
    fun getCityObserver() = citesLiveData

    fun searchCity(q: String) {
        launch {
            safeApiCallGenericLiveData(searchLiveData) {
                cityRepo.searchCity(q)
            }
        }
    }

    fun addCity(item: City) {
        viewModelScope.launch(Dispatchers.IO) {
            cityRepo.addCity(item)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}