package com.evolutions.jabar.footballclub.model

import com.google.gson.annotations.SerializedName

data class Teams(
        @SerializedName("idTeam")
        var teamId: String? = null,
        @SerializedName("strTeam")
        var teamName: String? = null,
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,
        @SerializedName("intFormedYear")
        var teamFormedYear: String? = null,
        @SerializedName("strStadium")
        var teamStadium: String? = null,
        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null
)