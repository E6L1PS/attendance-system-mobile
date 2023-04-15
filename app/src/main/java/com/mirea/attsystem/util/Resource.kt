package com.mirea.attsystem.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class Error<T>(message: String, data: T? = null, val exception: Exception? = null) : Resource<T>(data, message)

}