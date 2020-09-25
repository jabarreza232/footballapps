package com.evolutions.jabar.footballclub.sqlite.db
data class Favorite(val id:Long?,val eventId:String?,val teamHome:String?,val scoreHome:String?,val teamAway:String?,val scoreAway:String?,val date1:String?,val time1:String?){
    companion object {
        const val TABLE_FAVORITE:String = "TABLE_FAVORITE"
        const val ID:String = "ID_"
        const val EVENT_ID:String = "EVENT_ID"
        const val TEAM_HOME:String = "TEAM_HOME"
        const val SCORE_HOME:String ="SCORE_HOME"
        const val TEAM_AWAY:String =  "TEAM_AWAY"
        const val SCORE_AWAY:String = "SCORE_AWAY"
        const val DATE_EVENT:String = "DATE_EVENT"
        const val TIME_EVENT:String = "TIME_EVENT"
    }
}