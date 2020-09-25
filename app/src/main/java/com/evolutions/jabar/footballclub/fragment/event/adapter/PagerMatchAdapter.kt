package com.evolutions.jabar.footballclub.fragment.event.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.evolutions.jabar.footballclub.fragment.event.LastmatchFragment
import com.evolutions.jabar.footballclub.fragment.event.NextmatchFragment

class PagerMatchAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> LastmatchFragment()
            else->NextmatchFragment()
        }
    }
    override fun getCount(): Int = 2
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Last Match"
            else ->"Next Match"
        }
    }
}