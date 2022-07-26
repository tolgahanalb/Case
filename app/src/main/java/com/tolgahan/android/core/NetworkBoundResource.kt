package com.tolgahan.android.core

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.SuccessOffline(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.SuccessOnline(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.SuccessOffline(it) }
    }

    emitAll(flow)
}