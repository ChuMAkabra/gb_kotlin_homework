package com.example.dzchumanov06.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dzchumanov06.model.Weather

class MainViewModel(private val liveData : MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    fun getData() : LiveData<AppState> {
        getDataFromLocalSource()
        return liveData
    }

    fun getDataFromLocalSource() {
        liveData.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveData.postValue(AppState.Success(Weather()))
        }.start()
    }
}