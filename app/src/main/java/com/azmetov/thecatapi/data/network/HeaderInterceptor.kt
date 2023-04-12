package com.azmetov.thecatapi.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
    private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request());
        return response.newBuilder()
            .header("x-api-key", apiKey)
            .build();
    }
}