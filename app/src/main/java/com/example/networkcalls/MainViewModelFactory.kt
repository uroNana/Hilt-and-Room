package com.example.networkcalls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkcalls.network.Provider
import com.example.networkcalls.ui.MyViewModel

class MainViewModelFactory(private val provider: Provider): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)){
            return MyViewModel(provider) as T
        } else {
            throw IllegalStateException("unknown viewmodel")
        }
    }
}