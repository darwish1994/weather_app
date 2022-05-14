package com.currency.weatherapp.common.network.interceptor



import com.currency.weatherapp.BuildConfig
import com.currency.weatherapp.common.di.NetworkHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ResponseInterceptor @Inject constructor(private val networkHelper: NetworkHelper) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!networkHelper.isNetworkConnected()) throw  NoInternetConnection()

        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("key", BuildConfig.KEY)
            .build()

        val requestBuilder = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("accept", "application/json")
            .url(url)

        return chain.proceed(requestBuilder.build())
    }

    inner class NoInternetConnection() : IOException() {

        override val message: String
            get() = " تحقق من إتصال الانترنت "
    }
}
