package com.example.moviedetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviedetails.fragments.CurrentYearFragment
import com.example.moviedetails.fragments.PopularFragment

class ScreenAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int =2

    private val titles = listOf(
        PopularFragmentName,
        CurrentYearFragmentName
    )

    override fun getItem(position: Int): Fragment {
       return when(position){
           0-> PopularFragment()
           1-> CurrentYearFragment()
           else -> error("Position Not Defined")
       }
    }

    override fun getPageTitle(position: Int)= titles[position]
}