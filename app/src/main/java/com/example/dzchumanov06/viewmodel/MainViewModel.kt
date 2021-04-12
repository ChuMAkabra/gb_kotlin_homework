package com.example.dzchumanov06.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dzchumanov06.model.RepositoryImpl

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(), // инициализация LiveData
    private val repository: RepositoryImpl = RepositoryImpl() // инициализация репозитория
) : ViewModel() {

    fun getLiveData() = liveData

//    fun getWeatherFromLocalSource() = getDataFromLocalSource()

    fun getWeatherFromLocalSourceAll() = getDataFromLocalSource()

//    fun getWeatherFromRemoteSource()

    private fun getDataFromLocalSource() {
        liveData.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveData.postValue(AppState.Success(repository.getWeatherFromLocalStorageAll()))
        }.start()
    }
}