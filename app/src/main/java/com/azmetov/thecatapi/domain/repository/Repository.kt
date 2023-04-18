package com.azmetov.thecatapi.domain.repository

import androidx.paging.PagingData
import com.azmetov.thecatapi.domain.entity.CatEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getImages(): Flow<PagingData<CatEntity>>
    suspend fun saveToFavorites(catEntity: CatEntity)
    fun getFavorites(): Flow<List<CatEntity>>
    suspend fun deleteFavorite(catEntity: CatEntity)
}