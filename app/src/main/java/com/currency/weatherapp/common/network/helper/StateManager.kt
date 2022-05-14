package com.currency.weatherapp.common.network.helper


interface IViewState<T> {

    fun whichState(): CommonStates

    fun fetchData(): T?

    fun fetchError(): Int?
}


interface CommonStates

enum class CommonStatus : CommonStates {
    SUCCESS, LOADING, ERROR
}


data class NetworkResponse<T>(
    val status: CommonStates,
    val data: T?,
    val errorException: Int?
) :
    IViewState<T> {

    companion object {
        fun <T> success(data: T) = NetworkResponse<T>(CommonStatus.SUCCESS, data, null)

        fun <T> success() = NetworkResponse<T>(CommonStatus.SUCCESS, null, null)

        fun <T> error(error: Int) = NetworkResponse<T>(CommonStatus.ERROR, null, error)

        fun <T> loading() = NetworkResponse<T>(CommonStatus.LOADING, null, null)
    }

    override fun whichState(): CommonStates = status

    override fun fetchData(): T? = data

    override fun fetchError(): Int? = errorException

}

