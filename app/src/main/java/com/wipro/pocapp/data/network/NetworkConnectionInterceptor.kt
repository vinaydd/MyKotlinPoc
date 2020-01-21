package com.doctoremr.data.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.doctoremr.utils.Constants.NO_INTERNET
import com.wipro.pocapp.exception.NoInternetException

import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (!isInternetAvailable()) {
                Log.d("ExURL",chain.request().url().toString())
                throw NoInternetException(NO_INTERNET, "Make Sure you have an Active Data Connection")
            }
            return chain.proceed(chain.request())
        } catch (e: Exception) {
        }
        return chain.proceed(chain.request())
    }

    @Suppress("DEPRECATION")
    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let { cm ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            } else {
                connectivityManager.activeNetworkInfo.also {
                    result = it != null && it.isConnected
                }
            }
        }
        return result
    }
}