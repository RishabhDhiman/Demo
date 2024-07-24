package com.example.startwarblaster.dataclass

import com.google.gson.annotations.SerializedName

data class MatchInfoData (
    @SerializedName("match") var match : Int,
    @SerializedName("player1" ) var player1 : PlayerData,
    @SerializedName("player2" ) var player2 : PlayerData
)