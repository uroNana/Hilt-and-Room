package com.example.networkcalls

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkcalls.repository.JokeRepository
import com.example.networkcalls.usecase.MyViewModel


//class MainViewModelFactory(
//    private val jokeRepository: JokeRepository,
//    private val preferences: SharedPreferences,
//): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MyViewModel::class.java)){
//            return MyViewModel(jokeRepository, preferences) as T
//        } else {
//            throw IllegalStateException("unknown viewmodel")
//        }
//    }
//}