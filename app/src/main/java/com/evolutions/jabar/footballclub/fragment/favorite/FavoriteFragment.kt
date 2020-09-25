package com.evolutions.jabar.footballclub.fragment.favorite

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.fragment.favorite.adapter.PagerAdapterFavorite
import kotlinx.android.synthetic.main.fragment_favorite2.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PagerAdapterFavorite(childFragmentManager)
        pager_fav.adapter= adapter
        tab_favorite.setupWithViewPager(pager_fav)
    }
    companion object {
        fun newInstance():FavoriteFragment = FavoriteFragment()
    }

}
