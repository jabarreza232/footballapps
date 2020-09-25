package com.evolutions.jabar.footballclub.activity.main

import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.api.TheSportDbApi
import com.evolutions.jabar.footballclub.model.PlayerResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
        private val view: PlayerView,
        private val apiRespository: ApiRespository,
        private val gson: Gson) {
    fun getPlayerList(teamName: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRespository.doRequest(TheSportDbApi.getPlayers(teamName)).await(),
                    PlayerResponse::class.java)
            view.hideLoading()
            view.showPlayers(data.player)
        }
    }
}