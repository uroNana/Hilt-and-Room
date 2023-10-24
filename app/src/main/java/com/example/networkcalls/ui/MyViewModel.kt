package com.example.networkcalls.ui

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcalls.MyViewState
import com.example.networkcalls.network.Provider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

const val KEY_FIRST_TIME_USER = "first_time_user"

class MyViewModel(private val provider: Provider, private val preferences: SharedPreferences) : ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(MyViewState.IsLoading)

            val response = provider.getJokes()
            if (response != null) {
                state.emit(MyViewState.Result(response))
            } else {
                state.emit(MyViewState.NetworkError)
            }
        }
    }
}