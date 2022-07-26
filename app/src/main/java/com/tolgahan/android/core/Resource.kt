package com.tolgahan.android.core

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class SuccessOnline<T>(data: T) : Resource<T>(data)
    class SuccessOffline<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}