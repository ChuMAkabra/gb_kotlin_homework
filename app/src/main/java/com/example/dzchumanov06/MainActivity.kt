package com.example.dzchumanov06

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvHello = findViewById<TextView>(R.id.tvHello)
        val btnPushMe = findViewById<Button>(R.id.btnPushMe)

        btnPushMe.setOnClickListener {
            tvHello.text = getString(R.string.button_pushed)
            it.visibility = View.INVISIBLE
        }
    }
}