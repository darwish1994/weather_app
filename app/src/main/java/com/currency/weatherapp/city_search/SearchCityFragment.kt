package com.currency.weatherapp.city_search

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.viewModels
import com.currency.weatherapp.city_list.CityViewModel
import com.currency.weatherapp.city_list.list.CityAdapter
import com.currency.weatherapp.common.base.BaseBottomSheetFragmentMVVM
import com.currency.weatherapp.common.model.City
import com.currency.weatherapp.common.network.helper.CommonStatus
import com.currency.weatherapp.common.network.helper.IViewState
import com.currency.weatherapp.common.util.DialogUtils
import com.currency.weatherapp.common.util.OnItemClicked
import com.currency.weatherapp.common.util.observe
import com.currency.weatherapp.databinding.FragmentSearchCityBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * view enable user to search for city
 * and click on item to add city list
 * */

@AndroidEntryPoint
class SearchCityFragment : BaseBottomSheetFragmentMVVM<FragmentSearchCityBinding, CityViewModel>(),
    OnItemClicked {
    private val cityAdapter by lazy { CityAdapter() }


    override fun onCreateInit() {
        binding.citiesRec.adapter = cityAdapter
        cityAdapter.setListener(this)
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().length > 2)
                    getInitViewModel().searchCity(p0.toString())
                Log.v("SearchCityFragment", p0.toString())

            }
        })

        observe(getInitViewModel().getSearchObserver(), ::cityObserver)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        cityAdapter.setListener(null)
    }

    /**
     * observe call api status
     * show result of loading, success, error
     * */

    private fun cityObserver(it: IViewState<List<City>>?) {
        when (it?.whichState()) {

            CommonStatus.SUCCESS -> {
                it.fetchData()?.let { data ->
                    cityAdapter.updateData(data = data)
                }
            }
            CommonStatus.ERROR -> {
                DialogUtils.showErrorAlert(requireActivity(), {
                    getInitViewModel().searchCity(binding.searchEt.text.toString())
                }, {
                   dismiss()
                }, it.fetchError() ?: 0)
            }


        }

    }


    /**
     * if item is [City]
     * add it to room database
     * */
    override fun <T> onClick(item: T) {
        if (item is City) {
            getInitViewModel().addCity(item)
            dismiss()
        }

    }

    override fun getViewBinding(): FragmentSearchCityBinding =
        FragmentSearchCityBinding.inflate(layoutInflater)

    override fun initViewModel(): Lazy<CityViewModel> = viewModels()

}