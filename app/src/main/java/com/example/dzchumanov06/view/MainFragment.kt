package com.example.dzchumanov06.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.dzchumanov06.databinding.MainFragmentBinding
import com.example.dzchumanov06.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var _binding : MainFragmentBinding? = null
    // проксирующее свойство - создано, чтобы каждый раз в коде не использовать assert (_binding!!.)
    private val binding : MainFragmentBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // получим экземпляр нашей ViewModel, чтобы фрагмент мог на нее подписаться (вернее на ее LiveData)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // создаем Наблюдателя - вызывает события при обновлении данных в LiveData
        val observer = Observer<Any> { renderData(it) }
        // подписываемся на LiveData
        // viewLifecycleOwner - помогает следить за изменением состояния Activity или Fragment
        viewModel.getData().observe(viewLifecycleOwner, observer)
    }

    // обработка данных при их изменении
    fun renderData(liveData: Any) {
        Toast.makeText(context, "$liveData", Toast.LENGTH_LONG).show()
    }

    // во избежание утечек и нежелаемого поведения, обнулим _binding (в активити не нужно)
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}