package com.example.coroutinesflowdemo.repository

import com.example.coroutinesflowdemo.api.GankService
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class NewsRepository@Inject constructor(private val gankService: GankService,
                                        coroutinesContextProvider: CoroutinesContextProvider,
                                        connectivity: Connectivity): BaseRepository(coroutinesContextProvider, connectivity) {

    fun getGirlPictures(page: Int, pageCount: Int) =
        fetchData(showLoading = true) { gankService.getGirlPictures(page, pageCount) }

    fun getToken() = flow {
        Timber.d("james flow thread= ${Thread.currentThread().name}")
        val data = request { gankService.getToken() }
        emit(data)
    }

    fun login() = flow {
        val data = request { gankService.login() }
        emit(data)
    }

}