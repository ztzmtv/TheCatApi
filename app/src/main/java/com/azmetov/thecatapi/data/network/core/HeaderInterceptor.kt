package com.azmetov.thecatapi.data.network.core

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
    private val headers: List<Pair<String, String>>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return with(response.newBuilder()) {
            headers.forEach { header(it.first, it.second) }
            build()
        }
    }
}