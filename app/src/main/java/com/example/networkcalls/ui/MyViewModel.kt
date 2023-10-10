package com.example.networkcalls.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcalls.network.Data
import com.example.networkcalls.network.Provider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(private val provider: Provider) : ViewModel() {
    val result = MutableLiveData<Data?>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
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