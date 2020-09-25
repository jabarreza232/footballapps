package com.evolutions.jabar.footballclub.fragment.event.view

import com.evolutions.jabar.footballclub.model.Event

interface NextmatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<Event>?)

}
