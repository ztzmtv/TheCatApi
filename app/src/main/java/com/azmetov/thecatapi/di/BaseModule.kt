package com.azmetov.thecatapi.di

import androidx.paging.ExperimentalPagingApi
import com.azmetov.thecatapi.data.RepositoryImpl
import com.azmetov.thecatapi.data.db.AppDatabase
import com.azmetov.thecatapi.data.mapper.Mapper
import com.azmetov.thecatapi.data.network.core.ApiFactory
import com.azmetov.thecatapi.data.network.client.NetworkClient
import com.azmetov.thecatapi.data.network.client.NetworkClientImpl
import com.azmetov.thecatapi.data.paging.ImageRemoteMediator
import com.azmetov.thecatapi.domain.repository.Repository
import com.azmetov.thecatapi.presentation.imageloader.GlideLoader
import com.azmetov.thecatapi.presentation.imageloader.ImageLoader
import com.azmetov.thecatapi.presentation.ui.catlist.adapter.ImagesPagingAdapter
import com.azmetov.thecatapi.presentation.imageloader.Downloader
import com.azmetov.thecatapi.presentation.imageloader.ImageDownloader
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val baseModule = module {
    factory<NetworkClient> { NetworkClientImpl(get()) }

    single { ApiFactory.apiService }
    single { AppDatabase.getInstance(androidContext()).imagesDao() }
    single { AppDatabase.getInstance(androidContext()).favoritesDao() }
    single { Mapper() }

    factory<Repository> { RepositoryImpl(get(), get(), get(), get()) }
    factory { ImageRemoteMediator(get(), get(), get()) }

    factory { ImagesPagingAdapter(androidContext(), get()) }
    factory<ImageLoader> { GlideLoader() }
    factory<Downloader> { ImageDownloader(androidContext()) }

}