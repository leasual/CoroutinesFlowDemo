package com.example.coroutinesflowdemo.ui

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.coroutinesflowdemo.R
import com.example.coroutinesflowdemo.core.BaseActivity
import com.example.coroutinesflowdemo.extension.clicks
import com.example.coroutinesflowdemo.extension.retryWhen
import com.example.coroutinesflowdemo.extension.subscribe
import com.example.coroutinesflowdemo.extension.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: HomePageViewModel by viewModels()
    private val girlAdapter by lazy { GirlAdapter() }

    override fun getLayoutId(): Int = R.layout.activity_main


    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun setupViews() {

        rvGirlList.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            setHasFixedSize(true)
            adapter = girlAdapter
        }

        lifecycleScope.launch {
            viewModel.girlList.collectLatest {
                girlAdapter.submitData(it)
            }
        }

//        testForCollect()

//        textView.clicks()
//            .debounce(100)
//            .onEach {
//                textView.text = "click text= ${Random.nextInt(0, 10)}"
//            }.launchIn(lifecycleScope)
//
//        editText.textChanges()
//            .debounce(1000)
//            .onEach {
//                textView.text = it
//            }
//            .launchIn(lifecycleScope)
    }

   /* private fun testForCollect() {
        val flowInt = flowOf(1, 2, 3, 4)
        val flowString = flowOf("A", "B", "C", "D", "E")
        lifecycleScope.launch {
            Timber.d("james thread name= ${Thread.currentThread().name}")
            flowInt.zip(flowString) { intValue, stringValue ->
                Timber.d("james flowInt zip thread name= ${Thread.currentThread().name}")
                "$intValue$stringValue"
//                throw Exception("my error")
            }
                .onStart { Timber.d("james on start") }
                .flowOn(Dispatchers.IO)
                .retryWhen(2)
                .catch { Timber.d("james catch exception= ${it.message}") }
                .onCompletion { Timber.d("james on completed") }
                .onEach {
                    Timber.d("james collect thread name= ${Thread.currentThread().name}")
                    Timber.d("james zip flow= $it")
                    textView.text = it
                }
                .collect {
                    Timber.d("james collect thread name= ${Thread.currentThread().name}")
                    Timber.d("james zip flow= $it")
                    textView.text = it
                }
        }
    }

    private fun testForLaunchIn() {
        val flowInt = flowOf(1, 2, 3, 4)
        val flowString = flowOf("A", "B", "C", "D", "E")
        Timber.d("james thread name= ${Thread.currentThread().name}")
        flowInt.zip(flowString) { intValue, stringValue ->
            Timber.d("james flowInt zip thread name= ${Thread.currentThread().name}")
            "$intValue$stringValue"
//                throw Exception("my error")
        }
            .onStart { Timber.d("james on start") }
            .flowOn(Dispatchers.IO)
            .retryWhen(2)
            .catch { Timber.d("james catch exception= ${it.message}") }
            .onCompletion { Timber.d("james on completed") }
            .onEach {
                Timber.d("james collect thread name= ${Thread.currentThread().name}")
                Timber.d("james zip flow= $it")
                textView.text = it
            }
            .launchIn(lifecycleScope)
    }*/

    override fun observeData() {
        viewModel.loading.observe(this, Observer {
            Timber.d("james loading= $it")
            Toast.makeText(this, "show dialog= $it", Toast.LENGTH_SHORT).show()
        })
        viewModel.uiModel.subscribe(this) { uiModel ->
            uiModel?.girls?.let {
                Timber.d("james get data size= ${it.size}")
            }
        }
       /* viewModel.girlPictures.observe(this, Observer {
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
        })*/
    }
}