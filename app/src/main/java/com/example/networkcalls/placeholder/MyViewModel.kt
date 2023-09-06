package com.example.networkcalls.placeholder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    val result = MutableLiveData<Data>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun networkCall(){

        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO){

            var response = Repository.networkCall()
            if (response?.isSuccessful == true){
                isLoading.postValue(false)
                result.postValue(response.body())
            } else {
                Log.e("NETWORK ERROR","Couldn't achieve network call")
            }
        }
    }
}