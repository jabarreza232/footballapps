package com.evolutions.jabar.footballclub.activity.detail.team

import com.evolutions.jabar.footballclub.model.Teams

interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Teams>)
}