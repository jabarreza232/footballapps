package com.evolutions.jabar.footballclub.fragment.team

import com.evolutions.jabar.footballclub.model.Teams

interface TeamView{
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Teams>?)
}