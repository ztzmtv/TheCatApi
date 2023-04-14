package com.azmetov.thecatapi.di

import com.azmetov.thecatapi.presentation.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ImageViewModel(get())
    }
}