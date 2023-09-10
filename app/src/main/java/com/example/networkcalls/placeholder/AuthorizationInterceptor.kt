package com.example.networkcalls.placeholder

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("X-RapidAPI-Key","a56cf1c848mshb468a12ac5efdc7p18c930jsn18008f1bf6d3")
            .build()
        Log.i("AuthorizationInterceptor", "request: $newRequest")
        return chain.proceed(newRequest)
    }
}