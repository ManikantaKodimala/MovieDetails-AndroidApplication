package com.example.moviedetails

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout= findViewById<TabLayout>(R.id.home_screen_tab_layout)
        val viewPager =  findViewById<ViewPager>(R.id.home_screen_pager)
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