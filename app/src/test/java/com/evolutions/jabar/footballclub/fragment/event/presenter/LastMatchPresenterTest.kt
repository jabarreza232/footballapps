package com.evolutions.jabar.footballclub.fragment.event.presenter

import com.evolutions.jabar.footballclub.TestContextProvider
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.api.TheSportDbApi
import com.evolutions.jabar.footballclub.fragment.event.view.LastMatchView
import com.evolutions.jabar.footballclub.model.Event
import com.evolutions.jabar.footballclub.model.TeamEventResponse
import com.evolutions.jabar.footballclub.model.Teams
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {
@Mock
private lateinit var view:LastMatchView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRespository:ApiRespository
    private lateinit var presenter:LastMatchPresenter
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view,apiRespository,gson,TestContextProvider())
    }
    @Test
    fun testGetEventList() {
val event:MutableList<Event> = mutableListOf()
        val teams:MutableList<Teams> = mutableListOf()
        val response = TeamEventResponse(event, teams, event)
        val id = "4328"
        GlobalScope.launch {
        `when`(gson.fromJson(apiRespository.doRequest(TheSportDbApi.getLastMatch(id)).await(),
            TeamEventResponse::class.java
            )).thenReturn(response)

            presenter.getEventList(id)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLastMatchList(event)
            Mockito.verify(view).hideLoading()
        }
    }
}