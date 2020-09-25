package com.evolutions.jabar.footballclub.api

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ApiRespositoryTest {
    @Test
    fun testDoRequest() {
        val apiRepository = mock(ApiRespository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }

}