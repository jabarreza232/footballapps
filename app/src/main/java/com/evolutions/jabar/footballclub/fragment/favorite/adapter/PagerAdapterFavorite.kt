package com.evolutions.jabar.footballclub.fragment.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.evolutions.jabar.footballclub.fragment.favorite.FavoriteMatchFragment
import com.evolutions.jabar.footballclub.fragment.favorite.FavoriteTeamsFragment

class PagerAdapterFavorite(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> FavoriteMatchFragment()
            else -> FavoriteTeamsFragment()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Match"
            else -> "Team"
        }
    }

}
