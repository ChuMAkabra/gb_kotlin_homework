package com.example.dzchumanov06.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dzchumanov06.R
import com.example.dzchumanov06.model.WeatherManual

class FragmentMainAdapter(private var onItemViewClickListener: FragmentMain.OnItemViewClickListener?) :
    RecyclerView.Adapter<FragmentMainAdapter.MainViewHolder>() {

    private var weatherData: List<WeatherManual> = listOf()

    fun setWeather(data: List<WeatherManual>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cityImage = itemView.findViewById<ImageView>(R.id.cityImage)
        private val cityName = itemView.findViewById<TextView>(R.id.cityItem)


        fun bind(weather: WeatherManual) {
            cityImage.setImageResource(weather.city.image)
            cityName.text = weather.city.name

            cityImage.setOnClickListener { onItemViewClickListener?.onItemViewClick(weather) }
            cityName.setOnClickListener { onItemViewClickListener?.onItemViewClick(weather) }

        }
    }

    fun removeListener() {
        onItemViewClickListener = null
    }
}
