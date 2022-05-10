package com.albertomier.marveldemo.data.model

import com.google.gson.annotations.SerializedName

data class ThumbnailModel (
    @SerializedName("path") var path: String,
    @SerializedName("extension") var extension: String
)