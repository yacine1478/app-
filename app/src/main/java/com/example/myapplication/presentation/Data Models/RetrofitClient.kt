package com.example.myapplication.presentation.Data

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${getAuthToken()}")
                .build()
            chain.proceed(request)
        }
        .build()

    val instance: SolarApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)

            .build()
            .create(SolarApiService::class.java)
    }

    private fun getAuthToken(): String? {
        return "user_token_here"
    }
}