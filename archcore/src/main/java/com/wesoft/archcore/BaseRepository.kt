package com.wesoft.archcore

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.wesoft.archcore.util.Connectivity
import com.wesoft.archcore.util.CoroutinesContextProvider
import com.wesoft.archcore.util.ERROR
import com.wesoft.archcore.util.Resource
import retrofit2.Response

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

abstract class BaseRepository(private val coroutinesContextProvider: CoroutinesContextProvider,
                              private val connectivity: Connectivity
) {

    protected suspend fun <T, R: AbsBaseResponse<T>> request(call: suspend () -> Response<R>): Resource<T> {
        try {
            if (!connectivity.isNetworkConnected()) {
                return Resource.Error(
                    ERROR.NO_NETWORK_ERROR,
                    "no network"
                )
            }
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null && body.isSuccessful()) {
                    Resource.Success(
                        body.data()
                    )
                } else {
                    Resource.ServerError(
                        "[${body?.code()}] server error",
                        code = body?.code()
                    )
                }
            } else {
                return Resource.ServerError(
                    "[${response.code()}] server error",
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            return Resource.Error(
                ERROR.UNKNOWN_ERROR,
                "unknown error"
            )
        }
    }

    protected fun <T, R: AbsBaseResponse<T>> fetchData(showLoading: Boolean = true,
                                networkCall: suspend () -> Response<R>): LiveData<Resource<T>> =
        liveData(coroutinesContextProvider.io) {
            if (showLoading) {
                emit(Resource.Loading())
            }
            val source = request { networkCall.invoke() }
            if (source is Resource.Success) {
                emit(
                    Resource.Success(
                        source.data!!
                    )
                )
            } else {
                emit(source)
            }
        }

//    protected fun <T, A> fetchData(showLoading: Boolean = true,
//                                networkCall: suspend () -> Response<BaseResponse<A>>,
//                                saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
//        liveData(coroutinesContextProvider.io) {
//            if (showLoading) {
//                emit(Resource.Loading())
//            }
//            val source = request { networkCall.invoke() }
//            when(source) {
//                is Resource.Success -> saveCallResult(source.data!!)
//                is Resource.ServerError -> emit(Resource.ServerError<T>(source.message, source.code))
//                is Resource.Error -> emit(Resource.Error<T>(source.error, source.message))
//            }
//        }

//    protected fun <T, A> fetchData(showLoading: Boolean = true,
//                                   databaseQuery: () -> LiveData<T>,
//                                   networkCall: suspend () -> Response<BaseResponse<A>>,
//                                   saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
//        liveData(coroutinesContextProvider.io) {
//            if (showLoading) {
//                emit(Resource.Loading<T>())
//            }
//            val dataSource = databaseQuery.invoke().map { Resource.Success<T>(it) }
//            emitSource(dataSource)
//
//            val source = request { networkCall.invoke() }
//            when(source) {
//                is Resource.Success -> saveCallResult(source.data!!)
//                is Resource.ServerError -> emit(Resource.ServerError<T>(source.message, source.code))
//                is Resource.Error -> emit(Resource.Error<T>(source.error, source.message))
//            }
//        }
}