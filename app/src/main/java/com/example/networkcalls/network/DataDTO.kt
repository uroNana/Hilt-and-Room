package com.example.networkcalls.network

import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("joke")
    val joke: String?
) {
    fun toDomain(): Data {
        return Data(
            joke = joke

        )
    }
}

data class Data(
    val joke: String?
)