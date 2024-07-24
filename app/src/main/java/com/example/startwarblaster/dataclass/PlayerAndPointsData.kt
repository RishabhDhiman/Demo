package com.example.startwarblaster.dataclass

import com.google.gson.annotations.SerializedName

data class PlayerAndPointsData(@SerializedName("id") var id: Int, @SerializedName("name") var name: String, @SerializedName("icon") var iconURL: String, var score: Int)
