package com.evolutions.jabar.footballclub.activity.detail.team

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.R.id.add_to_favorite
import com.evolutions.jabar.footballclub.R.menu.match
import com.evolutions.jabar.footballclub.activity.main.PlayerActivity
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.model.Teams
import com.evolutions.jabar.footballclub.sqlite.db.FavoriteTeams
import com.evolutions.jabar.footballclub.sqlite.db.database
import com.evolutions.jabar.footballclub.view.invisible
import com.evolutions.jabar.footballclub.view.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Teams
    private lateinit var id: String
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        id = intent.getStringExtra("id")!!

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeColors(R.attr.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )
                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)
                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL
                            teamBadge = imageView {}.lparams(height = dip(75))

                            teamName = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = ContextCompat.getColor(context, android.R.color.holo_blue_dark)

                            }.lparams {
                                topMargin = dip(5)
                            }
                            teamFormedYear = textView {
                                this.gravity = Gravity.CENTER

                            }
                            teamStadium = textView {
                                this.gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(context, android.R.color.holo_blue_dark)

                            }


                            teamDescription = textView().lparams {
                                topMargin = dip(20)
                            }
                        }
                        progressBar = progressBar {

                        }.lparams {
                            centerHorizontally()
                        }


                    }
                }
            }
        }

        favoriteState()
        val request = ApiRespository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)
        swipeRefresh.onRefresh {
            presenter.getTeamDetail(id)

        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
                    .whereArgs("(TEAMS_ID={id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            if (!favorite.isEmpty()) isFavorite = true
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    @SuppressLint("SetTextI18n")
    override fun showTeamDetail(data: List<Teams>) {
        //display data on UI
        teams = Teams(
                data[0].teamId,
                data[0].teamName,
                data[0].teamBadge
        )


        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium

    }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(match, menu)
        menuItem = menu
        setFavorite()
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {

                if (isFavorite)
                    removeFromFavorite()
                else
                    addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun addToFavorite() {

        try {
            database.use {
                insert(FavoriteTeams.TABLE_FAVORITE_TEAMS,
                        FavoriteTeams.TEAMS_ID to teams.teamId,
                        FavoriteTeams.TEAM_NAME to teams.teamName,
                        FavoriteTeams.TEAM_BADGE to teams.teamBadge
                )
            }
            swipeRefresh.snackbar("Added To Favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeams.TABLE_FAVORITE_TEAMS, "(TEAMS_ID={id})",
                        "id" to id)
            }

            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorite)
    }

    private fun showPlayers() {
        startActivity<PlayerActivity>("team" to "${teams.teamName}")

    }

}

