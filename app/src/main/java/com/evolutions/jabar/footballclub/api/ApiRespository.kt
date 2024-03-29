package com.evolutions.jabar.footballclub.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

import java.net.URL

class ApiRespository {
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
         URL(url).readText()
    }
}