package com.azmetov.thecatapi.data.network.dto

import com.google.gson.annotations.SerializedName

data class ImageItemDto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("height") val height: Int,
    @field:SerializedName("width") val width: Int,
)
