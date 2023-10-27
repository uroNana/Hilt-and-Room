package com.example.networkcalls

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.networkcalls.network.Provider
import com.example.networkcalls.repository.JokeRepository

class MyApplication: Application() {
    private val apiKey = "a56cf1c848mshb468a12ac5efdc7p18c930jsn18008f1bf6d3"
    lateinit var database: AppDatabase
    lateinit var jokeRepository: JokeRepository
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var preferences: SharedPreferences
    private val provide = Provider(apiKey)

    override fun onCreate() {
        super.onCreate()
        preferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
        jokeRepository = JokeRepository(provide, database.repoDao())
        mainViewModelFactory = MainViewModelFactory(jokeRepository, preferences)
    }
}