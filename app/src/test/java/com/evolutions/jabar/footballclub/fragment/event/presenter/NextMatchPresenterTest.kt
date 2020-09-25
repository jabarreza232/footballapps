package com.evolutions.jabar.footballclub.fragment.event.presenter

import com.evolutions.jabar.footballclub.TestContextProvider
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.api.TheSportDbApi
import com.evolutions.jabar.footballclub.fragment.event.view.NextmatchView
import com.evolutions.jabar.footballclub.model.Event
import com.evolutions.jabar.footballclub.model.TeamEventResponse
import com.evolutions.jabar.footballclub.model.Teams
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest{
    @Mock
    private lateinit var view: NextmatchView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRespository: ApiRespository
    private lateinit var presenter:NextMatchPresenter
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view,apiRespository,gson, TestContextProvider())
    }
    @Test
    fun testGetNextMatchList() {
        val event:MutableList<Event> = mutableListOf()
        val teams:MutableList<Teams> = mutableListOf()
        val response = TeamEventResponse(event, teams, event)
        val id = "4328"
        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRespository.doRequest(TheSportDbApi.getLastMatch(id)).await(),
                    TeamEventResponse::class.java
            )).thenReturn(response)

            presenter.getNextMatchList(id)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showNextMatchList(event)
            Mockito.verify(view).hideLoading()
        }
    }
}