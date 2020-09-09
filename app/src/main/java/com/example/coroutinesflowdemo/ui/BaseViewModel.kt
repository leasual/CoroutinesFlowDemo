package com.example.coroutinesflowdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

abstract class BaseViewModel<T>: ViewModel() {
    val uiModel = MutableLiveData<T>()

    fun updateUiModel(data: T) {
        uiModel.postValue(data)
    }
}