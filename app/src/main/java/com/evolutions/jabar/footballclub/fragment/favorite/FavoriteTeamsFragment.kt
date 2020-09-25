package com.evolutions.jabar.footballclub.fragment.favorite

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout

import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.activity.detail.team.TeamDetailActivity
import com.evolutions.jabar.footballclub.fragment.favorite.adapter.FavoriteTeamsAdapter
import com.evolutions.jabar.footballclub.sqlite.db.FavoriteTeams
import com.evolutions.jabar.footballclub.sqlite.db.database

import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteTeamsFragment : Fragment() {
    private var favorites: MutableList<FavoriteTeams> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var listEvent: RecyclerView

    private lateinit var swipeRefresh: SwipeRefreshLayout
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamsAdapter(favorites) {
            requireContext().startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listEvent.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }


    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }


    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }

    }
/*
    fun loadFavoriteMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)?.commit()
        }
    }

*/




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(requireContext()))
    }
    @SuppressLint("ResourceAsColor")
   fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            rightPadding = dip(16)
            leftPadding = dip(16)
            orientation = LinearLayout.VERTICAL
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeColors(R.attr.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_red_light,
                        android.R.color.holo_orange_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    listEvent = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                }
            }

        }
    }


}