package com.azmetov.thecatapi.domain.repository

import androidx.paging.PagingData
import com.azmetov.thecatapi.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getImages(): Flow<PagingData<ImageEntity>>
    suspend fun saveToFavorites(imageEntity: ImageEntity)
    suspend fun getFavorites(): List<ImageEntity>
    suspend fun deleteFavorite(imageEntity: ImageEntity)
}