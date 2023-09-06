package com.example.networkcalls.placeholder

import okhttp3.Callback
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Repository {

    var endPoint: EndPoint? = null

    suspend fun networkCall(): Response<Data>? {
        if (endPoint == null){
            endPoint = createRetrofitInstance().create(EndPoint::class.java)
        }
        return endPoint?.networkCall()
    }



    fun createRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}
