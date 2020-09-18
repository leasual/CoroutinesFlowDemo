package com.wesoft.archcore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wesoft.archcore.util.Resource

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

abstract class BaseViewModel<T>: ViewModel() {

    val uiModel = MutableLiveData<T>()

    val loading = MutableLiveData(false)

    fun <R> handleResult(result: Resource<R?>, success: (R?) -> Unit) {
        when(result) {
            is Resource.Success -> success.invoke(result.data)
            is Resource.ServerError -> handleServerError(result)
            is Resource.Error -> handleError(result)
        }
    }

    fun <T> handleServerError(data: Resource.ServerError<T>) {

    }

    fun <T> handleError(data: Resource<T>) {

    }

    fun updateUiModel(data: T) {
        uiModel.postValue(data)
    }

    fun updateLoading(data: Boolean) {
        loading.postValue(data)
    }
}