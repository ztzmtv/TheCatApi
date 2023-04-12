package com.azmetov.thecatapi.data.network


import com.azmetov.thecatapi.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://api.thecatapi.com/v1/images/"
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val keyHeader = HeaderInterceptor(Constants.API_KEY)
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