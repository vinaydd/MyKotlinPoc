package com.doctoremr.data.network

import com.doctoremr.data.preferences.PreferenceProvider
import com.wipro.pocapp.BuildConfig
import com.wipro.pocapp.data.network.responses.test.BaseResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET("2iodh4vg0eortkl/facts.json")
    suspend fun getApiListDetail(): Response<BaseResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            prefs: PreferenceProvider
        ): NetworkApi {
            var requestInterceptor: Interceptor? = null
            prefs.getLogin()?.token?.let {
                requestInterceptor = Interceptor { chain ->
                    val url = chain.request()
                        .url()
                        .newBuilder()
                        .build()
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", it)
                        .url(url)
                        .build()
                    return@Interceptor chain.proceed(request)
                }
            }

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            }


            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(loggingInterceptor)
            requestInterceptor?.let { clientBuilder.addInterceptor(it) }

            val okkHttpclient = clientBuilder.build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkApi::class.java)
        }
    }

}