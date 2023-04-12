package com.azmetov.thecatapi.data.dto

import com.google.gson.annotations.SerializedName

data class Response(
    @field:SerializedName("Response") val response: List<ResponseItem?>? = null
)

data class ResponseItem(
    @field:SerializedName("width") val width: Int? = null,
    @field:SerializedName("id") val id: String? = null,
    @field:SerializedName("url") val url: String? = null,
    @field:SerializedName("height") val height: Int? = null
)
