package com.azmetov.thecatapi.data.network

sealed class NetworkResult {
    class Success<T>(val data: T) : NetworkResult()
    class Error(val message: String) : NetworkResult()
}