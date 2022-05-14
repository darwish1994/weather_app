package com.currency.weatherapp.city_list

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.currency.weatherapp.city_list.list.CityAdapter
import com.currency.weatherapp.common.base.BaseFragmentMVVM
import com.currency.weatherapp.common.model.City
import com.currency.weatherapp.common.util.OnItemClicked
import com.currency.weatherapp.common.util.observe
import com.currency.weatherapp.databinding.FragmentCityListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * view responsible for show all city
 * user has been selected to view their weather data
 * user can add new city by click button [add_city]
 * then search for city he / she wants to add
 * */

@AndroidEntryPoint
class CityListFragment : BaseFragmentMVVM<FragmentCityListBinding, CityViewModel>(), OnItemClicked {

    // adapter to view cities
    private val cityAdapter by lazy { CityAdapter() }


    override fun onCreateInit() {

        binding.citiesRec.adapter = cityAdapter

        cityAdapter.setListener(this) // init listener of adapter

        // show search fragment
        binding.addCityBtn.setOnClickListener {
            findNavController().navigate(CityListFragmentDirections.actionCityListFragmentToSearchCityFragment())
        }

        initObserver()

    }


    /**
     * - set observer for data changes
     * - data changes of cities from database
     * */
    private fun initObserver() {
        observe(getInitViewModel().getCityObserver(), ::observeCity)
    }


    private fun observeCity(it: List<City>?) {
        it?.let { data ->
            // update adapter with new data that stores in  room database
            cityAdapter.updateData(data)
        }

    }


    /**
     * if [item] is [City]
     * open whether details
     * */
    override fun <T> onClick(item: T) {

        if (item is City) {
            findNavController().navigate(
                CityListFragmentDirections.actionCityListFragmentToWeatherHistoricalFragment(
                    item.name,
                    item.name.plus(",").plus(item.country)
                )
            )

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        cityAdapter.setListener(null) // release listener from adapter
    }


    override fun getViewBinding(): FragmentCityListBinding =
        FragmentCityListBinding.inflate(layoutInflater)

    override fun initViewModel(): Lazy<CityViewModel> = viewModels()
}