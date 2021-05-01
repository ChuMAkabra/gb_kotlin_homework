package com.example.dzchumanov06.model

class RepositoryImpl: Repository {
    override fun getWeatherFromLocalStorage(): WeatherManual {
        // временно возвращаем значение по умолчанию
        return WeatherManual()

    }

    override fun getWeatherFromLocalStorageAll(): List<WeatherManual> {
        return getAllCities()
    }

//    override fun getWeatherFromServer(): Weather {
//        return
//    }
}