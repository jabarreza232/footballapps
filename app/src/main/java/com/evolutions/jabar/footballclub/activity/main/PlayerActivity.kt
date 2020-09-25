package com.evolutions.jabar.footballclub.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.activity.detail.player.DetailPlayerActivity
import com.evolutions.jabar.footballclub.activity.main.adapter.PlayerAdapter
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.model.Players
import com.evolutions.jabar.footballclub.view.invisible
import com.evolutions.jabar.footballclub.view.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayerActivity : AppCompatActivity(), PlayerView {
    private var player: MutableList<Players> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var teamName: String

    companion object {
        const val ITEM_DATA = ""
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeColors(R.attr.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    listPlayer = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)

                    }
                    progressBar = progressBar {

                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
        adapter = PlayerAdapter(this, player) {
            startActivity<DetailPlayerActivity>(ITEM_DATA to it)

        }
        val intent = intent
        teamName = intent.getStringExtra("team").toString()
        supportActionBar?.title = "List Player $teamName"
        listPlayer.adapter = adapter
        val request = ApiRespository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerList(teamName)

        swipeRefresh.onRefresh {
            presenter.getPlayerList(teamName)
        }
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayers(data: List<Players>) {
        swipeRefresh.isRefreshing = false
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()

    }
}


