package com.evolutions.jabar.footballclub.sqlite.db


data class FavoriteTeams(val id:Long?,val teamId:String?,val teamName:String?,val teamBadge:String?){
    companion object {
        const val TABLE_FAVORITE_TEAMS:String = "TABLE_FAVORITE_TEAMS"
        const val ID:String = "ID_"
        const val TEAMS_ID:String = "TEAMS_ID"
        const val TEAM_NAME:String = "TEAM_NAME"
        const val TEAM_BADGE:String = "TEAM_BADGE"

    }

}