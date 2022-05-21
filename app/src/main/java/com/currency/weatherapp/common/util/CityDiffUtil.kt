package com.currency.weatherapp.common.util

import androidx.recyclerview.widget.DiffUtil
import com.currency.weatherapp.domain.model.City

class CityDiffUtil(private val oldList: List<City>, private val newList: List<City>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].lat != newList[newItemPosition].lat -> false
            oldList[oldItemPosition].lon != newList[newItemPosition].lon -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].country != newList[newItemPosition].country -> false
            oldList[oldItemPosition].region != newList[newItemPosition].region -> false
            else -> true
        }
    }
}