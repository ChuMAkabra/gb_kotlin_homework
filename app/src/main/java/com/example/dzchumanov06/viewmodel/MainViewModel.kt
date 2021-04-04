package com.example.dzchumanov06.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val liveData : MutableLiveData<Any> = MutableLiveData()) : ViewModel() {

    fun getData() : LiveData<Any> {
        getDataFromLocalSource()
        return liveData
    }

    fun getDataFromLocalSource() {
        Thread {
            Thread.sleep(1000)
            liveData.postValue("something")
        }.start()
    }
}