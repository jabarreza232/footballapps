package com.evolutions.jabar.footballclub.activity.detail.event

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.R.id.add_to_favorite
import com.evolutions.jabar.footballclub.R.menu.match
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.model.Event
import com.evolutions.jabar.footballclub.model.Teams
import com.evolutions.jabar.footballclub.sqlite.db.Favorite
import com.evolutions.jabar.footballclub.sqlite.db.database
import com.evolutions.jabar.footballclub.view.DateTimeConverter
import com.evolutions.jabar.footballclub.view.invisible
import com.evolutions.jabar.footballclub.view.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_events.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar


class DetailEvents : AppCompatActivity(), DetailView {


    private lateinit var presenter: DetailPresenter
    private lateinit var eventId: String

    private lateinit var teamHome: String
    private lateinit var teamAway: String
    private lateinit var scoreHome: String
    private lateinit var scoreAway: String
    private lateinit var date: String
    private lateinit var time: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_events)
        supportActionBar?.title = ("Detail Pertandingan")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent

        eventId = intent.getStringExtra("id").toString()

        favoriteState()
        val gson = Gson()
        val request = ApiRespository()


        presenter = DetailPresenter(this, request, gson)
        presenter.getEventDetails(eventId)


    }


    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID={id})",
                            "id" to eventId
                    )
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


    override fun showLoading() {
        progressbar.visible()
    }

    override fun hideLoading() {
        progressbar.invisible()
    }


    override fun showBadgeHome(data: List<Teams>?) {
        if (data != null) {
            showImage(data[0])
        }

    }

    override fun showBadgeAway(data: List<Teams>?) {
        if (data != null) {
            showImageAway(data[0])
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showDetailEvent(data: List<Event>) {
        date_detail.text = DateTimeConverter.longDate(data[0].dateEvent!! + " " + data[0].timeEvent!!)
        time_detail.text = DateTimeConverter.timeDate(data[0].dateEvent!! + " " + data[0].timeEvent!!) + " WIB"
        name_home.text = data[0].teamHome
        name_away.text = data[0].teamAway
        val homeScore = if (data[0].scoreHome != null) "${data[0].scoreHome}" else ""
        val awayScore = if (data[0].scoreAway != null) "${data[0].scoreAway}" else ""

        score_home.text = homeScore
        score_away.text = awayScore
        ScoreTeamHome.text = data[0].scoreHome
        ScoreTeamAway.text = data[0].scoreAway
        GoalTeamHome.text = data[0].GoalTeamHome
        GoalTeamAway.text = data[0].GoalTeamAway
        GoalKeeperTeamHome1.text = data[0].GoalKeeperTeamHome
        GoalKeeperTeamAway1.text = data[0].GoalKeeperTeamAway
        DefenseTeamHome1.text = data[0].DefenseTeamHome
        DefenseTeamAway1.text = data[0].DefenseTeamAway
        MidfieldTeamHome1.text = data[0].MidfieldTeamHome
        MidfieldTeamAway1.text = data[0].MidfiedTeamAway
        ForwardTeamHome1.text = data[0].ForwardTeamHome
        ForwardTeamAway1.text = data[0].ForwardTeamAway
        SubstitutesTeamHome1.text = data[0].SubstitutesTeamHome
        SubstitutesTeamAway1.text = data[0].SubstitutesTeamAway

        presenter.getBadge("${data[0].teamHome}")

        presenter.getBadgeAway("${data[0].teamAway}")


        teamHome = "${data[0].teamHome}"
        teamAway = "${data[0].teamAway}"

        scoreHome = homeScore
        scoreAway = awayScore

        date = "${data[0].dateEvent}"
        time = "${data[0].timeEvent}"
    }


    fun showImage(teams: Teams) {
        Glide.with(applicationContext).load(teams.teamBadge).apply(RequestOptions().circleCrop().fitCenter().override(300, 300)).into(img_home)
    }

    fun showImageAway(teams: Teams) {
        Glide.with(applicationContext).load(teams.teamBadge).apply(RequestOptions().circleCrop().fitCenter().override(300, 300)).into(img_away)

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
        return when (item.itemId) {
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

    fun addToFavorite() {
        try {
            database.use {
                insert(
                        Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to eventId,
                        Favorite.TEAM_HOME to teamHome,
                        Favorite.SCORE_HOME to scoreHome,
                        Favorite.TEAM_AWAY to teamAway,
                        Favorite.SCORE_AWAY to scoreAway,
                        Favorite.DATE_EVENT to date,
                        Favorite.TIME_EVENT to time
                )
            }
            progressbar.snackbar("Added To Favorite").show()
        } catch (e: SQLiteConstraintException) {
            progressbar.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID={id})",
                        "id" to eventId
                )
            }
            progressbar.snackbar("Removed To Favorite").show()

        } catch (e: SQLiteConstraintException) {
            progressbar.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorite)

    }
}







