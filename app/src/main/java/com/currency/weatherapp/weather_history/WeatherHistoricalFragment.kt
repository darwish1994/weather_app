package com.currency.weatherapp.weather_history

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.currency.weatherapp.common.base.BaseFragmentMVVM
import com.currency.weatherapp.common.model.WeatherHour
import com.currency.weatherapp.common.network.helper.CommonStatus
import com.currency.weatherapp.common.network.helper.IViewState
import com.currency.weatherapp.common.network.response.HistoricalResponse
import com.currency.weatherapp.common.util.*
import com.currency.weatherapp.databinding.FragmentWeatherHistoricalBinding
import com.currency.weatherapp.weather_history.list.WeatherAdapter
import dagger.hilt.android.AndroidEntryPoint


/**
 * view enable user to see weather historical of current day
 * user can see weather status every hour at day
 * user can click item to see more details
 * **/

@AndroidEntryPoint
class WeatherHistoricalFragment :
    BaseFragmentMVVM<FragmentWeatherHistoricalBinding, WeatherViewModel>(), OnItemClicked,
    View.OnClickListener {

    private val weatherAdapter by lazy { WeatherAdapter() }
    private val arg by navArgs<WeatherHistoricalFragmentArgs>()

    override fun onCreateInit() {
        // set header title
        binding.titleTv.text = arg.location

        binding.histRec.adapter = weatherAdapter


        /**
         * fetch history data from api
         * */
        loadData()
        // set listener for data observed
        observe(getInitViewModel().getHistoricalLiveData(), ::historicalWeather)

        // init listener
        setListener()

    }


    private fun setListener() {
        binding.backBtn.setOnClickListener(this)
        weatherAdapter.setListener(this)
    }

    private fun removeListener() {
        binding.backBtn.setOnClickListener(null)
        weatherAdapter.setListener(null)
    }


    /**
     * get historical data for weather
     * using [city]
     * */
    private fun loadData() {
        getInitViewModel().getHistorical(arg.city)
    }

    private fun historicalWeather(it: IViewState<HistoricalResponse>?) {
        when (it?.whichState()) {
            CommonStatus.LOADING -> {

                binding.progress.toVisible()

            }
            CommonStatus.SUCCESS -> {
                binding.progress.toGone()
                it.fetchData()?.forecastday?.forecast?.first()?.weatherHour?.let { data ->
                    weatherAdapter.updateData(data)
                }
            }
            CommonStatus.ERROR -> {
                binding.progress.toGone()
                DialogUtils.showErrorAlert(requireActivity(), {
                    loadData()
                }, {
                    goBack()

                }, it.fetchError() ?: 0)

            }

        }


    }

    override fun <T> onClick(item: T) {
        if (item is WeatherHour)
            getInitViewModel().getHistoricalLiveData().value?.fetchData()?.city?.let {
                findNavController().navigate(
                    WeatherHistoricalFragmentDirections.actionWeatherHistoricalFragmentToWeatherDetailsFragment(
                        weather = item,
                        location = it.name.plus(",").plus(it.country)
                    )
                )
            }
    }


    override fun getViewBinding(): FragmentWeatherHistoricalBinding =
        FragmentWeatherHistoricalBinding.inflate(layoutInflater)

    override fun initViewModel(): Lazy<WeatherViewModel> = viewModels()
    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.backBtn.id -> goBack()
        }

    }

    override fun onDestroyView() {
        removeListener()
        super.onDestroyView()
    }

}