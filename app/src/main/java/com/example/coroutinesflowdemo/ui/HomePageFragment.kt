package com.example.coroutinesflowdemo.ui

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.coroutinesflowdemo.R
import com.example.coroutinesflowdemo.core.BaseFragment
import com.example.coroutinesflowdemo.extension.subscribe
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomePageFragment: BaseFragment() {

    private val viewModel: HomePageViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_home_page

    override fun setupViews() {

    }

    override fun observeData() {

        viewModel.loading.subscribe(this) {

        }

        viewModel.uiModel.subscribe(this) { uiModel ->
            uiModel?.girls?.let {
                Timber.d("james get data size= ${it.size}")
            }
        }
    }
}