package com.albertomier.marveldemo.data.model

import com.google.gson.annotations.SerializedName

data class HeroModel(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("thumbnail") var thumbnail: ThumbnailModel,
    @SerializedName("description") var description: String,
)