package com.currency.weatherapp.common.network.helper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.currency.weatherapp.common.network.interceptor.ResponseInterceptor
import com.currency.weatherapp.common.network.response.ResponseWrapper
import com.currency.weatherapp.common.util.NetworkCode
import com.currency.weatherapp.common.util.NetworkCode.CONVERSION_SERVER_ERROR
import com.currency.weatherapp.common.util.NetworkCode.HTTP_TIME_OUT_CODE
import com.currency.weatherapp.common.util.NetworkCode.SUCCESS_SERVER_CODE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


interface NetworkRemoteServiceCall {

    /**
     * safeApiCall
     * @param apiCall as suspend fn to call api
     * pass suspend api fn as parameter to safeApiCall fn
     * invoke Api at IO thread and handle logic
     * @return NetworkResponse<BaseResponse<T>>  hase success state data and failure state data
     */
    suspend fun <T> safeApiCallGenericLiveData(
        state: MutableLiveData<IViewState<T>>,
        apiCall: suspend () -> T
    ) {
        return withContext(Dispatchers.IO) {
            Log.v("SearchCityFragment", "start")
            try {
                state.postValue(NetworkResponse.loading())
                // invoke suspend service method
                val response = apiCall.invoke()
                Log.v("SearchCityFragment", "code : $response.")
                // get response code from http
                val httpCode = (response as? HttpException)?.response()?.code() ?: SUCCESS_SERVER_CODE
                Log.v("SearchCityFragment", "code : $httpCode")
                if (httpCode == SUCCESS_SERVER_CODE) {
                    state.postValue(NetworkResponse.success(response))
                } else {
                    state.postValue(NetworkResponse.error(httpCode))
//                    NetworkResponse.error(httpCode)
                }
            } catch (throwable: Exception) {


                if (throwable is ResponseInterceptor.NoInternetConnection || throwable is IOException) {
                    state.postValue(NetworkResponse.error(NetworkCode.NO_INTERNET_CODE))
//                    NetworkResponse.error(NetworkCode.NO_INTERNET_CODE)
                } else if (throwable is HttpException && throwable.code() == HTTP_TIME_OUT_CODE) {
                    state.postValue(NetworkResponse.error(NetworkCode.HTTP_TIME_OUT_CODE))
//                    NetworkResponse.error(HTTP_TIME_OUT_CODE)
                } else {
                    state.postValue(NetworkResponse.error(NetworkCode.CONVERSION_SERVER_ERROR))
//                    NetworkResponse.error(CONVERSION_SERVER_ERROR)
                }
            }
        }
    }

    /**
     * safeApiCall
     * @param apiCall as suspend fn to call api
     * pass suspend api fn as parameter to safeApiCall fn
     * invoke Api at IO thread and handle logic
     * @return NetworkResponse<BaseResponse<T>>  hase success state data and failure state data
     */
    suspend fun <T> safeApiCallGeneric(apiCall: suspend () -> T): NetworkResponse<ResponseWrapper<T>> {
        return withContext(Dispatchers.IO) {
            try {
                // invoke suspend service method
                val response = apiCall.invoke()
                // get response code from http
                val httpCode =
                    (response as? HttpException)?.response()?.code() ?: SUCCESS_SERVER_CODE

                if (httpCode == SUCCESS_SERVER_CODE)
                    NetworkResponse.success(
                        ResponseWrapper(
                            data = response!!,
                            status = true,
                            message = ""
                        )
                    ) else
                    NetworkResponse.error(httpCode)
            } catch (throwable: Exception) {


                if (throwable is ResponseInterceptor.NoInternetConnection || throwable is IOException)
                    NetworkResponse.error(NetworkCode.NO_INTERNET_CODE)
                else if (throwable is HttpException && throwable.code() == HTTP_TIME_OUT_CODE) {
                    NetworkResponse.error(HTTP_TIME_OUT_CODE)
                } else {
                    NetworkResponse.error(CONVERSION_SERVER_ERROR)
                }
            }
        }
    }

    /**
     * safeApiCall
     * @param apiCall as suspend fn to call api
     * pass suspend api fn as parameter to safeApiCall fn
     * invoke Api at IO thread and handle logic
     * @return NetworkResponse<BaseResponse<T>>  hase success state data and failure state data
     */
    suspend fun <T> safeApiCall(apiCall: suspend () -> ResponseWrapper<T>): NetworkResponse<ResponseWrapper<T>> {
        return withContext(Dispatchers.IO) {
            try {
                // invoke suspend service method
                val response = apiCall.invoke()
                // get response code from http
                val httpCode =
                    (response.data as? HttpException)?.response()?.code() ?: SUCCESS_SERVER_CODE

                if (httpCode == SUCCESS_SERVER_CODE)
                    NetworkResponse.success(
                        ResponseWrapper(
                            data = response.data!!,
                            status = true,
                            message = ""
                        )
                    ) else
                    NetworkResponse.error(httpCode)
            } catch (throwable: Exception) {


                if (throwable is ResponseInterceptor.NoInternetConnection || throwable is IOException)
                    NetworkResponse.error(NetworkCode.NO_INTERNET_CODE)
                else if (throwable is HttpException && throwable.code() == HTTP_TIME_OUT_CODE) {
                    NetworkResponse.error(HTTP_TIME_OUT_CODE)
                } else {
                    NetworkResponse.error(CONVERSION_SERVER_ERROR)
                }
            }
        }
    }


    /**
     * safeApiCall
     * @param apiCall as suspend fn to call api
     * pass suspend api fn as parameter to safeApiCall fn
     * invoke Api at IO thread and handle logic
     * @return NetworkResponse<BaseResponse<T>>  hase success state data and failure state data
     */
    suspend fun <T> safeApiCallWithoutBaseResponse(apiCall: suspend () -> Response<T>): NetworkResponse<ResponseWrapper<T>> {
        return withContext(Dispatchers.IO) {
            try {
                // invoke suspend service method
                val response = apiCall.invoke()
                // get response code from http
                val httpCode =
                    (response.body() as? HttpException)?.response()?.code() ?: SUCCESS_SERVER_CODE

                if (httpCode == SUCCESS_SERVER_CODE)
                    NetworkResponse.success(
                        ResponseWrapper(
                            data = response.body()!!,
                            status = true,
                            message = ""
                        )
                    ) else
                    NetworkResponse.error(httpCode)
            } catch (throwable: Exception) {


                if (throwable is ResponseInterceptor.NoInternetConnection || throwable is IOException)
                    NetworkResponse.error(NetworkCode.NO_INTERNET_CODE)
                else if (throwable is HttpException && throwable.code() == HTTP_TIME_OUT_CODE) {
                    NetworkResponse.error(HTTP_TIME_OUT_CODE)
                } else {
                    NetworkResponse.error(CONVERSION_SERVER_ERROR)
                }
            }
        }
    }

}