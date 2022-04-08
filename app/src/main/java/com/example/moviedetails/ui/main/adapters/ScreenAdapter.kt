package com.example.moviedetails.ui.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviedetails.fragments.CurrentYearFragment
import com.example.moviedetails.fragments.PopularFragment

class ScreenAdapter(private val titles:Array<String>,fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int =2


    override fun getItem(position: Int): Fragment {
       return when(position){
           0-> PopularFragment()
           1-> CurrentYearFragment()
           else -> error("Position Not Defined")
       }
    }

    override fun getPageTitle(position: Int)= titles[position]
}