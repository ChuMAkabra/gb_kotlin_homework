package com.example.dzchumanov06.model

data class WeatherCurrent (
    val weather: Array<Weather>,
    val main: Main?,
    val name: String?
)

data class Main (
    val temp: Float?
)

data class Weather (
    val description : String?, // Weather condition within the group (full list of weather conditions). Get the output in your language
    val icon : String? // Weather icon id
)