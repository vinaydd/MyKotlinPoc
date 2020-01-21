package com.doctoremr.data.network

import android.text.TextUtils
import android.util.Log

import com.wipro.pocapp.exception.ApiException
import com.wipro.pocapp.utils.Resource
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string().toString()
            Log.d("RequestURL", response.raw().request().url().toString())
            Log.d("ErrorCode", response.code().toString())
            Log.d("ErrorMessage", error)
            throw ApiException(response.code(), error)
        }
    }

    suspend fun <T : Any> apiRequestWithHeaders(call: suspend () -> Response<T>): Response<T> {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response
        } else {
            val error = response.errorBody()?.string()
            throw ApiException(response.code(), error.toString())
        }
    }

    suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val response = call.invoke()

            if (response.isSuccessful)
                return Resource.Success(response.body()!!)
            else {
                var message: String
                val jObjError = JSONObject(response.errorBody()!!.string())
                val error = jObjError.getJSONArray("errors")
                val jsonArray = error.get(0)
                val jsonObject = JSONObject(jsonArray.toString())
                message = jsonObject.getString("message")

                if (TextUtils.isEmpty(message)) {
                    message = response.message()
                }

                return Resource.Error(message)
            }


        } catch (se3: ApiException) {
            Resource.Error("ErrorWrapper")
        } catch (e: Throwable) {
            Resource.Error("ErrorWrapper")
        } catch (soket: SocketTimeoutException) {
            Resource.Error("Network ErrorWrapper")
        }
    }
}

