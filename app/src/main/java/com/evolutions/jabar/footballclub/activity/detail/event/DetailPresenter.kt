package com.evolutions.jabar.footballclub.activity.detail.event

import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.api.TheSportDbApi
import com.evolutions.jabar.footballclub.model.TeamEventResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPresenter(val view: DetailView,
                      val apiRespository: ApiRespository,
                      val gson: Gson) {
    fun getBadge(name: String?) {
        view.showLoading()
        GlobalScope.launch (Dispatchers.Main){
            val data = gson.fromJson(apiRespository.doRequest(TheSportDbApi.getTeams(name)).await(),
                    TeamEventResponse::class.java)
                view.hideLoading()
  if(data!=null) {
      view.showBadgeHome(data.teams)
  }
        }
    }

    fun getBadgeAway(nameAway: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRespository.doRequest(TheSportDbApi.getTeams(nameAway)).await(),
                    TeamEventResponse::class.java)
            view.hideLoading()
   if(data!=null) {
       view.showBadgeAway(data.teams)
   }
        }
    }
    fun getEventDetails(eventId:String?){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRespository.doRequest(TheSportDbApi.getEventDetail(eventId)).await(),
                    TeamEventResponse::class.java)
            view.hideLoading()

                    view.showDetailEvent(data.events)
        }
    }

}