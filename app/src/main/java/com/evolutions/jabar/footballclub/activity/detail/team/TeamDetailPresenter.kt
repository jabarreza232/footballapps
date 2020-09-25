package com.evolutions.jabar.footballclub.activity.detail.team

import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.api.TheSportDbApi
import com.evolutions.jabar.footballclub.model.TeamEventResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TeamDetailPresenter(private val view: TeamDetailView,
                    private val apiRepository: ApiRespository,
                    private val gson: Gson) {

    fun getTeamDetail(id: String?) {
        view.showLoading()

        GlobalScope.launch (Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getTeamsDetail(id)).await(),
                    TeamEventResponse::class.java)

            view.hideLoading()
            view.showTeamDetail(data.teams)
        }
    }

}