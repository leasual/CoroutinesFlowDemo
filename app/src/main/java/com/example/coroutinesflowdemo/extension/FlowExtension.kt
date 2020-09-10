package com.example.coroutinesflowdemo.extension

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesflowdemo.core.BaseViewModel
import com.example.coroutinesflowdemo.core.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber


/**
 * Create by james.li on 2020/9/10
 * Description:
 */

fun <T> Flow<T>.retryWhen(retryMaxTimes: Int = 3): Flow<T> {
    return this.retryWhen { cause, attempt ->
        Timber.d("james retryWhen count= $attempt retryMaxTimes= $retryMaxTimes")
        cause is Exception && attempt < retryMaxTimes
    }
}

@ExperimentalCoroutinesApi
fun <T> Flow<T>.loading(loading: MutableLiveData<Boolean>): Flow<T> {
    return this.onStart { loading.postValue(true) }
        .onCompletion { loading.postValue(false) }
}


@ExperimentalCoroutinesApi
fun <R, T: Resource<R>> Flow<T>.success(viewModel: BaseViewModel<*>, success: (R?) -> Unit): Flow<T> {
    return this.mapLatest {
        viewModel.handleResult(it, success)
        it
    }
}