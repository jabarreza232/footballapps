package com.evolutions.jabar.footballclub

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class TestContextProvider:CoroutineContextProvider(){
    @ExperimentalCoroutinesApi
    override val main:CoroutineContext=Dispatchers.Unconfined
}