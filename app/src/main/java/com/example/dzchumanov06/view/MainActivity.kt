package com.example.dzchumanov06.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dzchumanov06.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater) // создаем класс биндинга
        setContentView(binding.root)                    // передаем корневой макет
//        setContentView(R.layout.main_activity)        // viewBinding успешно заменяет findViewById
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(binding.container.id, FragmentMain.newInstance())
                    .commit()
        }
    }
}