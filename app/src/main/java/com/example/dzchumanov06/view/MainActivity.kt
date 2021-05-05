package com.example.dzchumanov06.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.dzchumanov06.BuildConfig

import com.example.dzchumanov06.R
import com.example.dzchumanov06.databinding.MainActivityBinding
import com.example.dzchumanov06.model.WeatherCurrent
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    private val WEATHER_URL_DOMAIN = "https://api.openweathermap.org/data/2.5"

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater) // создаем класс биндинга
        setContentView(binding.root)                          // передаем корневой макет

        // устанавливаем тулбар в Action Bar
        initToolbar()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, FragmentMain.newInstance())
                .commit()
        }
    }

    private fun initToolbar() {
        // устанавливаем тулбар в Action Bar
        setSupportActionBar(binding.toolbar)
        // не отображаем название активити в тулбаре
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        // реализуем функцию поиска города и вывода его данных во фрагмент
        val searchMenu = menu?.findItem(R.id.search)
        val searchView: SearchView = searchMenu?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                //TODO: загрузить данные для города (если такой имеется в базе)
                loadWeather()

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean { return false }
        })


        return true
    }

    private fun loadWeather() {
        // генерируем строку запроса
        val cityName = "Moscow"
        val apiCall =
            String.format("$WEATHER_URL_DOMAIN/weather?q=$cityName&units=metric&appid=${BuildConfig.WEATHER_API_KEY}")
        // создаем хендлер, чтобы с его помощью обратиться к UI потоку
        val handler = Handler()
        try {
            val url = URL(apiCall)
            // в отдельном потоке выполним запрос к серверу
            Thread(Runnable {
                lateinit var connection: HttpsURLConnection
                try {
                    // 1) Открываем соединение
                    connection = url.openConnection() as HttpsURLConnection
                    // 2) Подготоваливаем запрос
                    with(connection) {
                        // устанавливаем метод протокола - GET (получение данных)
                        requestMethod = "GET"
                        // устанавливаем таймаут (ожидание не больше 10 сек)
                        readTimeout = 10000
                        connect()
                    }
                    // 3) Читаем данные в поток
                    // читаем данные в ридер
                    val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
                    // записываем все данные ридера в одну строку
                    val inputLines: String = getLines(bufferedReader)
                    // конвертируем строку (содержащую JSON) в объект класса WeatherCurrent
                    val weatherCurrent: WeatherCurrent =
                        Gson().fromJson(inputLines, WeatherCurrent::class.java)
                    // возвращаемся в UI поток и выводим данные на экран
                    handler.post(Runnable {
                        Toast.makeText(
                            baseContext,
                            "${weatherCurrent.weather[0].description}",
                            Toast.LENGTH_LONG
                        ).show()
                    })
                } catch (e: Exception) {
                    Log.e("", "Something is wrong with connection")
                    e.printStackTrace()
                } finally {
                    connection.disconnect()
                }

            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Wrong URI")
            e.printStackTrace()
        }
    }

    private fun getLines(reader: BufferedReader): String {
        // получаем стрим строк из ридера (не путать стримы с InputStream. Это другая фича из Java 8.0)
        val streamOfStrings : Stream<String> = reader.lines()
        // создаем коллектор, объединяющий все строчки из стрима в одну, добавляя символ новой строки
        val collector : Collector<CharSequence, *, String> = Collectors.joining("\n")
        // объединяем стрим строк в одну большую строку с помощью предопределенного коллектора
        return streamOfStrings.collect(collector)
//        return reader.lines().collect(Collectors.joining("\n"))
    }
}