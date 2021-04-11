package com.example.dzchumanov06.model

class Weather(val city: City = City("Moscow", "pogoda.yandex.ru/moscow"),
              val temp : String = "7°C") {
}