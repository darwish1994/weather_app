package com.currency.weatherapp.common.util

import androidx.recyclerview.widget.DiffUtil
import com.currency.weatherapp.common.model.WeatherHour

class WeatherDiffUtil(private val oldList: List<WeatherHour>, private val newList: List<WeatherHour>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].timeEpoch == newList[newItemPosition].timeEpoch
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].time != newList[newItemPosition].time -> false
            oldList[oldItemPosition].tempC != newList[newItemPosition].tempC -> false
            oldList[oldItemPosition].condition.text != newList[newItemPosition].condition.text -> false

            else -> true
        }
    }
}