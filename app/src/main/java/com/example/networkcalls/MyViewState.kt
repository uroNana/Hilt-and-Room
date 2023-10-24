package com.example.networkcalls

import com.example.networkcalls.network.Data

sealed class MyViewState {
    data object IsLoading : MyViewState()
    data class Result(val data: Data) : MyViewState()
    data object NetworkError : MyViewState()
    data object IsFirstTimeUser : MyViewState()
}
