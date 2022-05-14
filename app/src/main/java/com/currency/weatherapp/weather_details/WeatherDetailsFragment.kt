package com.currency.weatherapp.weather_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.currency.weatherapp.R
import com.currency.weatherapp.common.base.BaseFragment
import com.currency.weatherapp.common.util.loadFrom
import com.currency.weatherapp.databinding.FragmentWeatherDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * view enable user to see details of weather item that has been selected
 *
 * **/

@AndroidEntryPoint
class WeatherDetailsFragment : BaseFragment<FragmentWeatherDetailsBinding>() {

    private val arg by navArgs<WeatherDetailsFragmentArgs>()
    override fun getViewBinding(): FragmentWeatherDetailsBinding =
        FragmentWeatherDetailsBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // location title
        binding.locationTv.text = arg.location


        // weather status icon
        binding.statusImg.loadFrom(arg.weather.condition.icon)

        // weather details
        binding.desTvVal.text = arg.weather.condition.text
        binding.tempVal.text = getString(R.string.temp_c, arg.weather.tempC.toString())
        binding.windVal.text = getString(R.string.wind_km_h, arg.weather.wind.toString())
        binding.humVal.text = "${arg.weather.humidity} %"

        //back click
        binding.backBtn.setOnClickListener {
            goBack()
        }

    }


}