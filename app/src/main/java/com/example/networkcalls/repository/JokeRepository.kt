package com.example.networkcalls.repository

import android.content.Context
import android.net.ConnectivityManager
import com.example.networkcalls.network.Data
import com.example.networkcalls.network.Provider
import com.example.networkcalls.repository.dao.RepoDao
import com.example.networkcalls.repository.entity.toDomain
import com.example.networkcalls.repository.entity.toEntity
import javax.inject.Inject
class JokeRepository @Inject constructor(
    private val provider: Provider,
    private val repodao: RepoDao,
    private val context: Context
) {
    suspend fun getJoke(): Data? {
        if (isNetworkAvailable()) {
            val response = provider.getJokes()
            val latestData = repodao.getLatestData()
            if (response != null) {
                val entity = response.toEntity()
                if (latestData == null) {
                    repodao.insertAll(entity)
                    return provider.getJokes()
                } else {
                    repodao.insertAll(entity)
                    return latestData.toDomain()
                }
            }
        }

        val randomJoke = getRandomJoke()
        if (randomJoke != null) {
            return randomJoke
        } else {
            return null
        }
    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private suspend fun getRandomJoke(): Data? {
        val randomJoke = repodao.getRandomJoke()
        return randomJoke?.toDomain()
    }
}