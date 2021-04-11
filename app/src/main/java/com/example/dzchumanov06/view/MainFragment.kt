package com.example.dzchumanov06.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dzchumanov06.R
import com.example.dzchumanov06.databinding.MainFragmentBinding
import com.example.dzchumanov06.viewmodel.AppState
import com.example.dzchumanov06.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel // ViewModel будет инициализирована позже

    private var _binding: MainFragmentBinding? = null

    // проксирующее свойство - создано, чтобы каждый раз в коде не использовать assert (_binding!!.)
    private val binding: MainFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // получим экземпляр нашей ViewModel, чтобы фрагмент мог на нее подписаться (вернее на ее LiveData)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // создаем Наблюдателя - вызывает события при обновлении данных в LiveData
        val observer = Observer<AppState> { renderData(it) }
        // подписываемся на LiveData
        // viewLifecycleOwner - помогает следить за изменением состояния Activity или Fragment
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        // изменяем данные, хранящиеся в LiveData (чтобы запустить процесс вывода на экран)
        viewModel.getWeatherFromLocalSource()
    }

    // обработка данных при их изменении
    fun renderData(liveData: AppState) {
        binding.tvDetails.visibility = View.VISIBLE
        when (liveData) {
            is AppState.Success -> {
                binding.tvTemp.text = liveData.weatherData.temp
                binding.tvCity.text = liveData.weatherData.city.name
                binding.ivIcon.visibility = View.VISIBLE
                binding.tvDetails.text = getString(R.string.details)
                binding.tvDetails.setOnClickListener(View.OnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://${liveData.weatherData.city.link}")
                        )
                    )
                })

            }
            is AppState.Error -> binding.tvDetails.text = getString(R.string.error)
            AppState.Loading -> binding.tvDetails.text = getString(R.string.loading)
        }
    }

    // во избежание утечек и нежелаемого поведения, обнулим _binding (в активити не нужно)
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}