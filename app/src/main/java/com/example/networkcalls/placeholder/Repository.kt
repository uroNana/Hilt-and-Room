package com.example.networkcalls.placeholder

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.annotations.SerializedName



object Repository {
    private var endPoint: EndPoint? = null

    fun getEndPoint(): EndPoint {
        if (endPoint == null) {
            val authorization = AuthorizationInterceptor()
            val client = OkHttpClient.Builder().addInterceptor(authorization).connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

            endPoint = Retrofit.Builder()
                .baseUrl("https://chuck-norris-by-api-ninjas.p.rapidapi.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EndPoint::class.java)
        }
        return endPoint!!
    }

    suspend fun networkCall(): Response<Data>? {
        return try {
            getEndPoint().networkCall()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
