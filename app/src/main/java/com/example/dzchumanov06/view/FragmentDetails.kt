package com.example.dzchumanov06.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dzchumanov06.R
import com.example.dzchumanov06.databinding.FragmentDetailsBinding
import com.example.dzchumanov06.model.WeatherManual
import com.google.android.material.snackbar.Snackbar

class FragmentDetails : Fragment() {

    companion object {

        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): FragmentDetails {
            val fragment = FragmentDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentDetailsBinding? = null

    // проксирующее свойство - создано, чтобы каждый раз в коде не использовать assert (_binding!!.)
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.getParcelable<WeatherManual>(BUNDLE_EXTRA)



        if (weather != null) {
            binding.tvDetails.visibility = View.VISIBLE
            binding.tvTemp.text = weather.temp
            binding.tvCity.text = weather.city.name
            binding.ivIcon.visibility = View.VISIBLE
            binding.tvDetails.text = getString(R.string.details)
            binding.tvDetails.setOnClickListener(View.OnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://${weather.city.link}")
                    )
                )
            })

            /**
             * временный код для домашнего задания №4:
             * вышаем клик-листенер на иконку - по нажатии:
             * 1) ее внешний вид изменится
             * 2) вызовется пользовательская extension-функция testSnackBar
             */
            with(binding.ivIcon) {
                setOnClickListener {
                    setImageResource(R.drawable.thunderstorm)
                    testSnackBar("The icon has been changed", Snackbar.LENGTH_LONG, Color.RED)
                }
            }

        }
    }

    // экспериментальная extension-функция
    private fun View.testSnackBar(text: String, duration: Int, color: Int) {
        Snackbar.make(this, text, duration).setTextColor(color).show()
    }

    // во избежание утечек и нежелаемого поведения, обнулим _binding (в активити не нужно)
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}