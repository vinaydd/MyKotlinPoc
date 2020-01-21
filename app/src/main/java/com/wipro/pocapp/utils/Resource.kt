package com.wipro.pocapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Status
) {
    class Success<T>(data: T) : Resource<T>(data, status = Status.SUCCESS)
    class Loading<T>(data: T? = null) : Resource<T>(data, status = Status.LOADING)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message, status = Status.ERROR)
    class CustomError<T>(data: T) : Resource<T>(data,  status = Status.ERROR)


    enum class Status {
        SUCCESS, ERROR, LOADING
    }

}