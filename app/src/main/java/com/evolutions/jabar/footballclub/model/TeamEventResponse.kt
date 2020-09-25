package com.evolutions.jabar.footballclub.model

data class TeamEventResponse(
        val events: List<Event>,
        val teams: List<Teams>,
        val event: List<Event>
)