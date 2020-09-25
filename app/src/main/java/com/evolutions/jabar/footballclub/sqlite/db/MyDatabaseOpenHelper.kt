package com.evolutions.jabar.footballclub.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper (ctx,"FavoriteEvent.db",null ,1){
    companion object {
        private var instance: MyDatabaseOpenHelper?= null
        @Synchronized
       fun getInstance(ctx: Context) : MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
    db?.createTable(Favorite.TABLE_FAVORITE,true,
         Favorite.ID to INTEGER + PRIMARY_KEY+ AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.TEAM_HOME to TEXT,
            Favorite.SCORE_HOME to TEXT,
            Favorite.TEAM_AWAY to TEXT,
            Favorite.SCORE_AWAY to TEXT,
            Favorite.DATE_EVENT to TEXT,
            Favorite.TIME_EVENT to TEXT
        )
        db!!.createTable(FavoriteTeams.TABLE_FAVORITE_TEAMS,true,
                FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeams.TEAMS_ID to TEXT + UNIQUE,
                FavoriteTeams.TEAM_NAME to TEXT,
                FavoriteTeams.TEAM_BADGE to TEXT

        )



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE,true)
        db!!.dropTable(FavoriteTeams.TABLE_FAVORITE_TEAMS,true)

    }

}


val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)