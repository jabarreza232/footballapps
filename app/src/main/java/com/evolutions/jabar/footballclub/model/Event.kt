package com.evolutions.jabar.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        @SerializedName("idEvent")
        var eventId: String? = null,
        @SerializedName("strEvent")
        var eventName: String? = null,
        @SerializedName("strHomeTeam")
        var teamHome: String? = null,
        @SerializedName("intHomeScore")
        var scoreHome: String? = null,
        @SerializedName("strAwayTeam")
        var teamAway: String? = null,
        @SerializedName("intAwayScore")
        var scoreAway: String? = null,
        @SerializedName("strHomeGoalDetails")
        var GoalTeamHome: String? = null,
        @SerializedName("strAwayGoalDetails")
        var GoalTeamAway: String? = null,
        @SerializedName("strHomeLineupGoalkeeper")
        var GoalKeeperTeamHome: String? = null,
        @SerializedName("strAwayLineupGoalkeeper")
        var GoalKeeperTeamAway: String? = null,
        @SerializedName("strHomeLineupDefense")
        var DefenseTeamHome: String? = null,
        @SerializedName("strAwayLineupDefense")
        var DefenseTeamAway: String? = null,
        @SerializedName("strHomeLineupMidfield")
        var MidfieldTeamHome: String? = null,
        @SerializedName("strAwayLineupMidfield")
        var MidfiedTeamAway: String? = null,
        @SerializedName("strHomeLineupForward")
        var ForwardTeamHome: String? = null,
        @SerializedName("strAwayLineupForward")
        var ForwardTeamAway: String? = null,
        @SerializedName("strHomeLineupSubstitutes")
        var SubstitutesTeamHome: String? = null,
        @SerializedName("strAwayLineupSubstitutes")
        var SubstitutesTeamAway: String? = null,
        @SerializedName("dateEvent")
        var dateEvent: String? = null,
        @SerializedName("strTime")
        var timeEvent: String? = null


) : Parcelable