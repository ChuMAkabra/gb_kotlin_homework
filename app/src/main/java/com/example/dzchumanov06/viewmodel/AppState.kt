package com.example.dzchumanov06.viewmodel

sealed class AppState {
    data class Success(val weatherData: Any): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState() // не содержит данных, можно сделать object’ом. Иначе - data class
}