package com.azmetov.thecatapi.data.dto

import com.google.gson.annotations.SerializedName

data class ImageItemDto(
    @field:SerializedName("id") val id: String? = null,
    @field:SerializedName("url") val url: String? = null,
    @field:SerializedName("height") val height: Int? = null,
    @field:SerializedName("width") val width: Int? = null,
)
