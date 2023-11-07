package com.example.networkcalls

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.networkcalls.network.Provider
import com.example.networkcalls.repository.JokeRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application()