package com.evolutions.jabar.footballclub.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.fragment.favorite.adapter.PagerAdapterFavorite
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        requireActivity().invalidateOptionsMenu()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PagerAdapterFavorite(childFragmentManager)
        pager_fav.adapter = adapter
        tab_favorite.setupWithViewPager(pager_fav)
    }

    companion object {
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }
}
