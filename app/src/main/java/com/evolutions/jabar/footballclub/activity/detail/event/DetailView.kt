package com.evolutions.jabar.footballclub.activity.detail.event

import com.evolutions.jabar.footballclub.model.Event
import com.evolutions.jabar.footballclub.model.Teams

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showBadgeHome(data: List<Teams>?)
    fun showBadgeAway(data: List<Teams>?)
    fun showDetailEvent(data:List<Event>)

}