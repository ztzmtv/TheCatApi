package com.azmetov.thecatapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.azmetov.thecatapi.domain.entity.ImageEntity
import com.azmetov.thecatapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ImageViewModel(
    private val repository: Repository
) : ViewModel() {

    val flow = repository.getImages().cachedIn(viewModelScope)

    fun addToFavorites(imageEntity: ImageEntity) {
        viewModelScope.launch {
            repository.saveToFavorites(imageEntity)
        }
    }

    fun deleteFavorite(imageEntity: ImageEntity) {
        viewModelScope.launch {
            repository.deleteFavorite(imageEntity)
        }
    }

    fun getFavorites(): Flow<List<ImageEntity>> {
        return repository.getFavorites()
    }
}