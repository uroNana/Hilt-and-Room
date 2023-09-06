package com.example.networkcalls.placeholder

import retrofit2.Response
import retrofit2.http.GET

interface EndPoint {
    @GET("jokes/random")
    suspend fun networkCall(): Response<Data>
}