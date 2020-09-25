package com.evolutions.jabar.footballclub.api

import android.net.Uri
import com.evolutions.jabar.footballclub.BuildConfig

object TheSportDbApi {
    fun getLastMatch(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=$league"
    }

    fun getNextMatch(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=$league"
    }
    fun getTeams(name: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=$name"
        /*       return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchteams.php")
                .appendQueryParameter("t",name)
                .build()
                .toString()
 */

    }
    fun searchEvent(event_name:String?):String{
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/searchevents.php?e=$event_name"
    }
    fun getAllTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }
    fun getTeamsDetail(teamId:String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id",teamId)
                .build()
                .toString()

    }
    fun getPlayers(teamName:String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchplayers.php")
                .appendQueryParameter("t",teamName)
                .build()
                .toString()
    }
fun getEventDetail(eventId:String?):String{
    return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id",eventId)
            .build()
            .toString()
}

}