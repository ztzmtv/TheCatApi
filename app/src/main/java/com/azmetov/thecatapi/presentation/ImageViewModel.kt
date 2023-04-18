package com.azmetov.thecatapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.azmetov.thecatapi.domain.entity.CatEntity
import com.azmetov.thecatapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ImageViewModel(
    private val repository: Repository
) : ViewModel() {

    val flow = repository.getImages().cachedIn(viewModelScope)

    fun addToFavorites(catEntity: CatEntity) {
        viewModelScope.launch {
            repository.saveToFavorites(catEntity)
        }
    }

    fun deleteFavorite(catEntity: CatEntity) {
        viewModelScope.launch {
            repository.deleteFavorite(catEntity)
        }
    }

    fun getFavorites(): Flow<List<CatEntity>> {
        return repository.getFavorites()
    }
}