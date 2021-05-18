package dev.dizzy1021.core.data.source

import dev.dizzy1021.core.utils.EspressoIdlingResource
import dev.dizzy1021.core.utils.ResponseWrapper
import dev.dizzy1021.core.utils.State
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<ResponseWrapper<ResultType>> = flow {
        emit(ResponseWrapper.pending(null))
        EspressoIdlingResource.increment()

        val dbSource = loadFromDB().first()

        EspressoIdlingResource.decrement()
        if (shouldFetch(dbSource)) {

            emit(ResponseWrapper.pending(null))
            EspressoIdlingResource.increment()

            val apiResponse = createCall().first()
            when (apiResponse.state) {
                State.SUCCESS -> {
                    apiResponse.data?.let { saveCallResult(it, dbSource) }
                    emitAll(loadFromDB().map { ResponseWrapper.success(it) })
                }
                State.FAILURE -> {
                    onFetchFailed()
                    emit(ResponseWrapper.failure<ResultType>(apiResponse.message.toString(), null))
                }
            }
            EspressoIdlingResource.decrement()
        } else {
            EspressoIdlingResource.increment()
            emitAll(loadFromDB().map { ResponseWrapper.success(it) })
            EspressoIdlingResource.decrement()
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ResponseWrapper<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType, save: ResultType)

    fun asFlow(): Flow<ResponseWrapper<ResultType>> = result
}