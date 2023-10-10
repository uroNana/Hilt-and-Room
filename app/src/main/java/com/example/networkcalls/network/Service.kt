package com.example.networkcalls.network

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("v1/chucknorris")
    suspend fun getJoke(): Response<DataDTO>
}