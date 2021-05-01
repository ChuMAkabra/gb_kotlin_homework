package com.example.dzchumanov06.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.dzchumanov06.R
import com.example.dzchumanov06.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

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
            return false
            }

            override fun onQueryTextChange(newText: String?): Boolean { return false }

        })


        return true
    }
}