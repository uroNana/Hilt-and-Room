package com.example.networkcalls.network

import com.example.networkcalls.MyApplication
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Provider(apiKey: String) {

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(AuthorizationInterceptor(apiKey))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://chuck-norris-by-api-ninjas.p.rapidapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(Service::class.java)

    suspend fun getJokes(): Data? {
        val response = service.getJoke()
        return response.body()?.toDomain()
    }
}