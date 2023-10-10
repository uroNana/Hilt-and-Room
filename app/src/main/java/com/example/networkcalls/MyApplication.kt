package com.example.networkcalls

import android.app.Application
import com.example.networkcalls.network.Provider

class MyApplication: Application() {


    private val apiKey = "a56cf1c848mshb468a12ac5efdc7p18c930jsn18008f1bf6d3"
    private val provide = Provider(apiKey)
    val factory = MainViewModelFactory(provide)
    override fun onCreate() {
        super.onCreate()

    }
}