package com.azmetov.thecatapi.data.network


import com.azmetov.thecatapi.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val apiKeyKey = "x-api-key"
    private const val contentTypeKey = "Content-Type"
    private const val contentTypeValue = "application/json"
    private const val BASE_URL = "https://api.thecatapi.com/v1/images/"

    private val header1 = Pair(apiKeyKey, Constants.API_KEY)
    private val header2 = Pair(contentTypeKey, contentTypeValue)

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val keyHeader = HeaderInterceptor(listOf(header1, header2))
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(keyHeader)

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .build()

    val apiService: ImagesApi = retrofit.create(ImagesApi::class.java)
}