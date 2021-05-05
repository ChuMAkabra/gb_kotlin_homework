package com.example.dzchumanov06.model

interface Repository {
    fun getWeatherFromLocalStorage(): WeatherManual
    fun getWeatherFromLocalStorageAll(): List<WeatherManual>
//    fun getWeatherFromServer(): Weather
}