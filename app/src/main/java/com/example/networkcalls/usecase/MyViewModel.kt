package com.example.networkcalls.usecase

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcalls.usecase.state.MyViewState
import com.example.networkcalls.network.Provider
import com.example.networkcalls.repository.JokeRepository
import com.example.networkcalls.repository.dao.RepoDao
import com.example.networkcalls.repository.entity.toDomain
import com.example.networkcalls.repository.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

const val KEY_FIRST_TIME_USER = "first_time_user"

class MyViewModel(
    private val jokeRepository: JokeRepository,
    private val preferences: SharedPreferences
) : ViewModel() {

    val state = MutableSharedFlow<MyViewState>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(MyViewState.IsLoading)
        }
    }
        fun checkFirstTimeUser() {
            val firstTimeUser = preferences.getBoolean(KEY_FIRST_TIME_USER, true)
            if (firstTimeUser) {
                preferences.edit().putBoolean(KEY_FIRST_TIME_USER, false).apply()
                viewModelScope.launch(Dispatchers.IO) {
                    state.emit(MyViewState.IsFirstTimeUser)
                }
            }
        }
        fun getJoke() {
            Log.d("MyViewModel", "getJoke")

            viewModelScope.launch(Dispatchers.IO) {
                val result = jokeRepository.getJoke()
                state.emit(MyViewState.Result(result))
            }
        }

}