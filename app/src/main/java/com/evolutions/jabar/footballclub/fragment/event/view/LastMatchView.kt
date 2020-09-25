package com.evolutions.jabar.footballclub.fragment.event.view

import com.evolutions.jabar.footballclub.model.Event

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data: List<Event>?)

}
