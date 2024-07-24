package com.example.startwarblaster.dataclass

import com.google.gson.annotations.SerializedName

data class PlayerData(
    @SerializedName("id") var id: Int,
    @SerializedName("score") var score: Int,
    var name: String
)