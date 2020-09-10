package com.example.coroutinesflowdemo.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen
import timber.log.Timber


/**
 * Create by james.li on 2020/9/10
 * Description:
 */

fun <T> Flow<T>.retryWhen(retryMaxTimes: Int = 3): Flow<T> {
    return this.retryWhen { cause, attempt ->
        Timber.d("james retryWhen count= $attempt retryMaxTimes= $retryMaxTimes")
        return@retryWhen cause is Exception && attempt < retryMaxTimes
    }
}
