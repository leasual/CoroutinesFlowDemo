package com.example.coroutinesflowdemo.ui

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.coroutinesflowdemo.R
import com.example.coroutinesflowdemo.repository.ERROR
import com.example.coroutinesflowdemo.repository.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: HomePageViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_main


    override fun setupViews() {

    }

    override fun observeData() {
        viewModel.girlPictures.observe(this, Observer {
            Timber.d("james observe data")

            when (it) {
                is Resource.Success -> {
                    Timber.d("james data size ${it.data?.size}")
                }
                is Resource.Error -> {
                    when (it.error) {
                        ERROR.NO_NETWORK_ERROR -> {
                            Timber.e("james error= no network")
                        }
                        ERROR.NETWORK_ERROR -> {
                            Timber.e("james error= network error")
                        }
                        ERROR.UNKNOWN_ERROR -> {
                            Timber.e("james error= unknown error")
                        }
                    }
                }
                is Resource.Loading -> {
                    Timber.d("james loading")
                }
                is Resource.ServerError -> {
                    Timber.e("james server error= ${it.message}")
                }
            }
        })
    }
}