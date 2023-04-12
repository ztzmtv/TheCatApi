package com.azmetov.thecatapi.di

import com.azmetov.thecatapi.data.network.core.ApiFactory
import com.azmetov.thecatapi.data.network.client.NetworkClient
import com.azmetov.thecatapi.data.network.client.NetworkClientImpl
import org.koin.dsl.module

val baseModule = module {
    factory<NetworkClient> { NetworkClientImpl(get()) }
    factory { ApiFactory.apiService }
}