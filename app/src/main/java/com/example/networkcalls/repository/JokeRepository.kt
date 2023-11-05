package com.example.networkcalls.repository

import com.example.networkcalls.network.Data
import com.example.networkcalls.network.Provider
import com.example.networkcalls.repository.dao.RepoDao
import com.example.networkcalls.repository.entity.toDomain
import com.example.networkcalls.repository.entity.toEntity

class JokeRepository(private val provider: Provider,
                     private val repoDao: RepoDao
) {
    suspend fun getJoke(): Data? {
        val response = provider.getJokes()
        val latestData = repoDao.getLatestData()
        return if (response != null) {
            val entity = response.toEntity()
            if (latestData == null) {
                repoDao.insertAll(entity)
                provider.getJokes()
            } else {
                repoDao.insertAll(entity)
                latestData.toDomain()
            }
        } else {
            getRandomJoke()
        }
    }


    private suspend fun getRandomJoke(): Data? {
        val randomJoke = repoDao.getRandomJoke()
        return randomJoke?.toDomain()
    }
}


