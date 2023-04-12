package com.azmetov.thecatapi.data.network.client

import com.azmetov.thecatapi.data.dto.ImageItemDto

interface NetworkClient {
    suspend fun getImages(page: Int, limit: Int): List<ImageItemDto>
}