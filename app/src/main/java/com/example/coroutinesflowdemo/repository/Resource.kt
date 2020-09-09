package com.example.coroutinesflowdemo.repository

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

enum class ERROR {
    NETWORK_ERROR,
    NO_NETWORK_ERROR,
    UNKNOWN_ERROR
}

sealed class Resource<out T> {
    data class Success<out T>(val data: T?): Resource<T>()
    data class Error<out T>(val error: ERROR, val message: String): Resource<T>()
    data class ServerError<out T>(val message: String, val code: Int? = -1): Resource<T>()
    data class Loading<out T>(val data: T? = null): Resource<T>()
}