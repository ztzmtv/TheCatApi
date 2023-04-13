package com.azmetov.thecatapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.azmetov.thecatapi.domain.repository.Repository

class ImageViewModel(
    private val repository: Repository
) : ViewModel() {

   val flow = repository.getImages().cachedIn(viewModelScope)

}