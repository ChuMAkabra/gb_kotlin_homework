package com.example.dzchumanov06.model

import android.os.Parcelable
import com.example.dzchumanov06.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherManual(
    val city: City = getDefaultCity(),
    val temp: String = "7°C"
) : Parcelable

fun getDefaultCity() = City("Moscow", "pogoda.yandex.ru/moscow", R.drawable.moscow)

fun getAllCities(): List<WeatherManual> {
    return listOf(
        WeatherManual(City("Moscow", "pogoda.yandex.ru/moscow", R.drawable.moscow), "7°C"),
        WeatherManual(City("Barcelona", "pogoda.yandex.ru/barcelona", R.drawable.barcelona), "15°C"),
        WeatherManual(City("Paris", "pogoda.yandex.ru/paris", R.drawable.paris), "13°C"),
        WeatherManual(City("New York", "pogoda.yandex.ru/new-york", R.drawable.new_york), "9°C")
    )
}
