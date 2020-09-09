package com.example.coroutinesflowdemo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.coroutinesflowdemo.model.GirlResp
import com.example.coroutinesflowdemo.repository.NewsRepository
import com.example.coroutinesflowdemo.repository.Resource

class HomePageViewModel@ViewModelInject constructor(val repository: NewsRepository): ViewModel() {

    var girlPictures = repository.getGirlPictures(1, 10)

}