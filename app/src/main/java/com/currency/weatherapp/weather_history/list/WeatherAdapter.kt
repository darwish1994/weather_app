package com.currency.weatherapp.weather_history.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.currency.weatherapp.R
import com.currency.weatherapp.common.base.BaseViewHolder
import com.currency.weatherapp.common.model.WeatherHour
import com.currency.weatherapp.common.util.OnItemClicked
import com.currency.weatherapp.common.util.WeatherDiffUtil
import com.currency.weatherapp.common.util.loadFrom
import com.currency.weatherapp.common.util.setViewText
import com.currency.weatherapp.databinding.ItemLayoutWeatherHistoricalBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val dataList = arrayListOf<WeatherHour>()

    private var listener: OnItemClicked? = null

    fun setListener(onItemClicked: OnItemClicked?) {
        listener = onItemClicked
    }

    fun updateData(data: List<WeatherHour>) {
        val diffUtil = WeatherDiffUtil(dataList, data)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        dataList.apply {
            clear()
            addAll(data)
        }
        diffResults.dispatchUpdatesTo(this)
    }


    inner class WeatherViewHolder(private val layout: ItemLayoutWeatherHistoricalBinding) :
        BaseViewHolder<WeatherHour>(layout.root) {
        private lateinit var weatherHour: WeatherHour

        init {
            layout.root.setOnClickListener {
                if (::weatherHour.isInitialized)
                    listener?.onClick(weatherHour)
            }

        }

        override fun bind(item: WeatherHour) {
            weatherHour = item
            layout.dateTv.text = weatherHour.time
            layout.statusTv.setViewText(R.string.weather_status, weatherHour.condition.text, weatherHour.tempC)
            layout.iconImg.loadFrom(weatherHour.condition.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
        WeatherViewHolder(
            ItemLayoutWeatherHistoricalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}