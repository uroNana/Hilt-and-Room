package com.example.networkcalls.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.networkcalls.repository.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo")
    fun getAll(): Flow<List<RepoEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg jokes: RepoEntity)
    @Query("SELECT * FROM repo ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomJoke(): RepoEntity?

    @Query("SELECT * FROM repo ORDER BY id DESC LIMIT 1")
    suspend fun getLatestData(): RepoEntity?
}