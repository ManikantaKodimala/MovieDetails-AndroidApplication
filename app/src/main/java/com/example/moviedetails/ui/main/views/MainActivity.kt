package com.example.moviedetails.ui.main.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedetails.R
import com.example.moviedetails.databinding.ActivityMainBinding
import com.example.moviedetails.ui.main.adapters.ScreenAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabLayout =  binding.homeScreenTabLayout
        val viewPager = binding.homeScreenPager
        val pagerAdapter = ScreenAdapter(resources.getStringArray(R.array.titles_array),supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter=pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_search){
            startActivity(Intent(applicationContext, SearchableActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}