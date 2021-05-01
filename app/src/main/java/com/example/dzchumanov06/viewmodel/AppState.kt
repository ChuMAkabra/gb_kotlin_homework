package com.example.dzchumanov06.viewmodel

import com.example.dzchumanov06.model.WeatherManual

sealed class AppState {
    data class Success(val weatherData: List<WeatherManual>): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState() // не содержит данных, можно сделать object’ом. Иначе - data class
}