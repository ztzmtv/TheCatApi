package com.azmetov.thecatapi.data.network.core

import com.azmetov.thecatapi.data.network.dto.ImageItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("search")
    suspend fun search(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<ImageItemDto>

}