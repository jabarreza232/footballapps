package com.evolutions.jabar.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Players(
        @SerializedName("idPlayer")
        var playerId:String? = null,
        @SerializedName("strPlayer")
        var playerName:String? = null,
        @SerializedName("strTeam")
        var playerTeamName:String? = null,
        @SerializedName("strDescriptionEN")
        var playerDescription:String?=null,
        @SerializedName("strPosition")
        var playerPosition:String?= null,
        @SerializedName("strHeight")
        var playerHeight:String?=null,
        @SerializedName("strWeight")
        var playerWeight:String?= null,
        @SerializedName("strCutout")
        var playerImage:String?=null,
        @SerializedName("strFanart1")
        var playerImageDetails:String?=null

) : Parcelable