package com.example.networkcalls.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcalls.network.Data
import com.example.networkcalls.network.Provider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val KEY_FIRST_TIME_USER = "first_time_user"

class MyViewModel(private val provider: Provider, private val preferences: SharedPreferences) : ViewModel() {
    val result = MutableLiveData<Data?>()
    val isLoading = MutableLiveData<Boolean>()
    val isFirstTimeUser = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
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

        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO){

            var response = provider.getJokes()
            if (response != null){
                isLoading.postValue(false)
                result.postValue(response)
            } else {
                Log.e("NETWORK ERROR","Couldn't achieve network call")
            }
        }
    }
}