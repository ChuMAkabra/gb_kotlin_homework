package com.example.dzchumanov06.model

interface Repository {
    fun getWeatherFromLocalStorage(): Weather
    fun getWeatherFromLocalStorageAll(): List<Weather>
//    fun getWeatherFromServer(): Weather
}