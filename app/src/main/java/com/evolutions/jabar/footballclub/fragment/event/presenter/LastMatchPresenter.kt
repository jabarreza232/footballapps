package com.evolutions.jabar.footballclub.fragment.event.presenter

import com.evolutions.jabar.footballclub.CoroutineContextProvider
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.api.TheSportDbApi
import com.evolutions.jabar.footballclub.fragment.event.view.LastMatchView
import com.evolutions.jabar.footballclub.model.TeamEventResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LastMatchPresenter(private val view: LastMatchView,
                         private val apiRespository: ApiRespository,
                         private val gson: Gson,private val context:CoroutineContextProvider = CoroutineContextProvider()) {
    fun getEventList(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRespository.doRequest(TheSportDbApi.getLastMatch(league)).await(),
                    TeamEventResponse::class.java)
            if (data != null) {
                view.hideLoading()
                view.showLastMatchList(data.events)
            }
        }
    }
    fun searchEvent(search: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRespository.doRequest(TheSportDbApi.searchEvent(search)).await(),
                    TeamEventResponse::class.java)
            view.hideLoading()
            view.showLastMatchList(data.event)


        }


    }
}

