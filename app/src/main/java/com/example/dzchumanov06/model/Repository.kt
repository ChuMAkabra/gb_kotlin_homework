package com.example.dzchumanov06.model

interface Repository {
    fun getWeatherFromLocalStorage(): Weather
//    fun getWeatherFromServer(): Weather
}