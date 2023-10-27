package com.example.networkcalls.network

import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val joke: String?
) {
    fun toDomain(): Data {

        return Data(
            id = id,
            joke = joke

        )
    }
}