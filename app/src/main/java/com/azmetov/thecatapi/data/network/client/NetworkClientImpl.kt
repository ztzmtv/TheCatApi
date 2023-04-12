package com.azmetov.thecatapi.data.network.client

import com.azmetov.thecatapi.data.dto.ImageItemDto
import com.azmetov.thecatapi.data.network.core.ImagesApi

class NetworkClientImpl(
    private val imagesApi: ImagesApi
) : NetworkClient {
    override suspend fun getImages(page: Int, limit: Int): List<ImageItemDto> {
        return imagesApi.search(page = page, limit = limit)
    }
}