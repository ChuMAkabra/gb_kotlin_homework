package com.example.dzchumanov06.model

class RepositoryImpl: Repository {
    override fun getWeatherFromLocalStorage(): Weather {
        // временно возвращаем значение по умолчанию
        return Weather()

    }

    override fun getWeatherFromLocalStorageAll(): List<Weather> {
        return getAllCities()
    }

//    override fun getWeatherFromServer(): Weather {
//        return
//    }
}