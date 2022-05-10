package com.albertomier.marveldemo.data.model

import com.google.gson.annotations.SerializedName

data class HeroListResponse(
    @SerializedName("code") var id: Int,
    @SerializedName("status") var status: String,
    @SerializedName("data") var data: HeroListData
)

