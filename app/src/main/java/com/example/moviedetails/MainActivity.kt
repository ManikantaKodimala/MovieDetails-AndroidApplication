package com.example.moviedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.moviedetails.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        val tabLayout = binding.homeScreenTabLayout
//        val viewPager = binding.homeScreenPager
        val tabLayout= findViewById<TabLayout>(R.id.home_screen_tab_layout)
        val viewPager =  findViewById<ViewPager>(R.id.home_screen_pager)
        val pagerAdapter = ScreenAdapter(supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter=pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}