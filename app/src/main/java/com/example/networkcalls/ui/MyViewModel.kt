package com.example.networkcalls.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcalls.network.Data
import com.example.networkcalls.network.Provider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

const val KEY_FIRST_TIME_USER = "first_time_user"

class MyViewModel(private val provider: Provider, private val preferences: SharedPreferences) : ViewModel() {
    val result = MutableSharedFlow<Data?>()
    val isLoading = MutableSharedFlow<Boolean>()
    val isFirstTimeUser = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.emit(false)
        }
    }
    init {
        checkFirstTimeUser(preferences)
    }
    private fun checkFirstTimeUser(preferences: SharedPreferences) {
        val firstTimeUser = preferences.getBoolean(KEY_FIRST_TIME_USER, true)
        if(firstTimeUser) {
            preferences.edit().putBoolean(KEY_FIRST_TIME_USER, false).apply()
            isFirstTimeUser.value = true
        }
    }

    fun getJoke(){
        viewModelScope.launch(Dispatchers.IO){
            isLoading.emit(true)

            var response = provider.getJokes()
            if (response != null){
                isLoading.emit(false)
                result.emit(response)
            } else {
                Log.e("NETWORK ERROR","Couldn't achieve network call")
            }
        }
    }
}