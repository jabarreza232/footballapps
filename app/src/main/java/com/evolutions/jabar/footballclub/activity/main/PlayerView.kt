package com.evolutions.jabar.footballclub.activity.main

import com.evolutions.jabar.footballclub.model.Players

interface PlayerView{
    fun showLoading()
    fun hideLoading()
    fun showPlayers(data:List<Players>)

}