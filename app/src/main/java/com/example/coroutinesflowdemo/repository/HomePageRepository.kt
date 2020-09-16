package com.example.coroutinesflowdemo.repository

import com.example.coroutinesflowdemo.api.AppService
import com.wesoft.archcore.BaseRepository
import com.wesoft.archcore.util.Connectivity
import com.wesoft.archcore.util.CoroutinesContextProvider
import com.wesoft.archcore.util.Resource
import com.example.coroutinesflowdemo.model.GirlResp
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class HomePageRepository@Inject constructor(private val gankService: AppService,
                                            coroutinesContextProvider: CoroutinesContextProvider,
                                            connectivity: Connectivity
): BaseRepository(coroutinesContextProvider, connectivity) {

    fun getGirlPictures(page: Int, pageCount: Int) =
        fetchData(showLoading = true) { gankService.getGirlPictures(page, pageCount) }

    fun getGirlPicturesFlow(page: Int, pageCount: Int) = flow<Resource<List<GirlResp>>> {
        val data = request { gankService.getGirlPictures(page, pageCount) }
        emit(data)
    }

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