package com.evolutions.jabar.footballclub.fragment.event

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.fragment.event.adapter.PagerMatchAdapter
import com.evolutions.jabar.footballclub.fragment.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = PagerMatchAdapter(childFragmentManager)
        pager_match.adapter= adapter
        tab_match.setupWithViewPager(pager_match)
    }
    companion object {
        fun newInstance(): MatchFragment {
            return MatchFragment()
        }
    }
}
