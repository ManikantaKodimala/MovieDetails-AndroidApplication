package com.example.moviedetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ScreenAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int =2

    private val titles = listOf<String>(
        "Popular",
        "Current Year"
    )

    override fun getItem(position: Int): Fragment {
       return when(position){
           0->PopularFragment()
           1->CurrentYearFragment()
           else -> error("Position Not Defined")
       }
    }

    override fun getPageTitle(position: Int)= titles[position]
}