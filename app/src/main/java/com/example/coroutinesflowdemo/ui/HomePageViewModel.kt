package com.example.coroutinesflowdemo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesflowdemo.model.GirlResp
import com.example.coroutinesflowdemo.repository.NewsRepository
import com.example.coroutinesflowdemo.repository.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.lang.Exception

class HomePageViewModel@ViewModelInject constructor(val repository: NewsRepository): ViewModel() {

//    var girlPictures = repository.getGirlPictures(1, 10)
    val loading = MutableLiveData<Boolean>(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun login() {
        repository.getToken()
            .onStart {
                Timber.d("james on start")
                loading.postValue(true)
            }
            .flatMapConcat {
                Timber.d("james token end it= $it")
                if (it is Resource.Success) {
                    repository.login()
                } else {
                    Timber.d("james token error end it= $it")
                    throw Exception("get token error")
                }
            }
            .catch {
                Timber.d("james error= ${it.message}")
                it.printStackTrace()
            }
            .onCompletion {
                Timber.d("james on completed")
                loading.postValue(false)
            }.launchIn(viewModelScope)
    }
}