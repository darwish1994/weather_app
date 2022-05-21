package com.currency.weatherapp.presenter.city_list.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.currency.weatherapp.R
import com.currency.weatherapp.common.base.BaseViewHolder
import com.currency.weatherapp.domain.model.City
import com.currency.weatherapp.common.util.CityDiffUtil
import com.currency.weatherapp.common.util.OnItemClicked
import com.currency.weatherapp.common.util.setViewText
import com.currency.weatherapp.databinding.ItemLayoutCiteBinding

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val dataList = arrayListOf<City>()
    private var listener: OnItemClicked? = null

    fun setListener(onItemClicked: OnItemClicked?) {
        listener = onItemClicked
    }

    fun updateData(data: List<City>) {
        val diffUtil = CityDiffUtil(dataList, data)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        dataList.apply {
            clear()
            addAll(data)
        }
        diffResults.dispatchUpdatesTo(this)
    }


    inner class CityViewHolder(private val layout: ItemLayoutCiteBinding) :
        BaseViewHolder<City>(layout.root) {

        private lateinit var city: City

        init {
            layout.root.setOnClickListener {
                if (::city.isInitialized)
                    listener?.onClick(city)
            }

        }

        override fun bind(item: City) {
            city = item
            layout.titleTv.setViewText(R.string.city_title, item.name, item.region, item.country)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(
            ItemLayoutCiteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}