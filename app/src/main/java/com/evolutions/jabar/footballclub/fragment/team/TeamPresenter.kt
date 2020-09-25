package com.evolutions.jabar.footballclub.fragment.team

import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.api.TheSportDbApi
import com.evolutions.jabar.footballclub.model.TeamEventResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TeamPresenter(private val view: TeamView?,
                    private val apiRepository: ApiRespository,
                    private val gson: Gson) {

    fun getTeamList(league: String?) {
        view?.showLoading()

     GlobalScope.launch (Dispatchers.Main) {
     val data = gson.fromJson(apiRepository
             .doRequest(TheSportDbApi.getAllTeams(league)).await(),
             TeamEventResponse::class.java)

         view?.hideLoading()
         if(data!=null) {
             view?.showTeamList(data.teams)
     }
         }
    }
    fun searchTeam(search:String?){
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDbApi.getTeams(search)).await(),
                    TeamEventResponse::class.java)
            view?.hideLoading()
            view?.showTeamList(data.teams)

        }
    }

}