package com.example.dzchumanov06.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dzchumanov06.R
import com.example.dzchumanov06.databinding.FragmentMainBinding
import com.example.dzchumanov06.viewmodel.AppState
import com.example.dzchumanov06.viewmodel.MainViewModel

class FragmentMain : Fragment() {
    companion object {
        fun newInstance() = FragmentMain()
    }

    private lateinit var viewModel: MainViewModel // ViewModel будет инициализирована позже

    private var _binding: FragmentMainBinding? = null

    // проксирующее свойство - создано, чтобы каждый раз в коде не использовать assert (_binding!!.)
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val adapter = FragmentMainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rvCity.adapter = adapter
        binding.rvCity.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        // получим экземпляр нашей ViewModel, чтобы фрагмент мог на нее подписаться (вернее на ее LiveData)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // создаем Наблюдателя - вызывает события при обновлении данных в LiveData
        val observer = Observer<AppState> { renderData(it) }
        // подписываемся на LiveData
        // viewLifecycleOwner - помогает следить за изменением состояния Activity или Fragment
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        // изменяем данные, хранящиеся в LiveData (чтобы запустить процесс вывода на экран)
//        viewModel.getWeatherFromLocalSource()
        viewModel.getWeatherFromLocalSourceAll()
    }

    // обработка данных при их изменении
    fun renderData(liveData: AppState) {
        when (liveData) {
            is AppState.Success -> {
//                Toast.makeText(context, "Ta-daaaa!", Toast.LENGTH_SHORT).show()
                adapter.setWeather(liveData.weatherData)
            }
            is AppState.Error -> Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
            AppState.Loading -> Toast.makeText(context, getString(R.string.loading), Toast.LENGTH_SHORT).show()
        }
    }

    // во избежание утечек и нежелаемого поведения, обнулим _binding (в активити не нужно)
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}