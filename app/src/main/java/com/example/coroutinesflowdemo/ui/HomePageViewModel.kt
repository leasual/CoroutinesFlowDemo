package com.example.coroutinesflowdemo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.coroutinesflowdemo.api.AppService
import com.wesoft.archcore.BaseViewModel
import com.wesoft.archcore.extension.loading
import com.wesoft.archcore.extension.success
import com.example.coroutinesflowdemo.model.GirlResp
import com.example.coroutinesflowdemo.repository.HomePageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import timber.log.Timber

class HomePageViewModel@ViewModelInject constructor(
    private val gankService: AppService,
    private val repository: HomePageRepository) : BaseViewModel<HomePageViewModel.HomePageUiModel>() {

    val girlList: Flow<PagingData<GirlResp>> = Pager(PagingConfig(pageSize = 10)) {
        GirlPagingSource(gankService)
    }.flow.cachedIn(viewModelScope)


    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getGirlPictures() {
        repository.getGirlPicturesFlow(1, 10)
            .flowOn(Dispatchers.IO)
            .loading(loading)
            .catch {
                Timber.d("james error= ${it.message}")
                it.printStackTrace()
            }
            .success(this) {
                updateUiModel(HomePageUiModel(girls = it))
            }.launchIn(viewModelScope)
    }


    data class HomePageUiModel(
        val girls: List<GirlResp>? = null
    )
}