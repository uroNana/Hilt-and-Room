package com.example.networkcalls

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.networkcalls.repository.dao.RepoDao
import com.example.networkcalls.repository.entity.RepoEntity

@Database(entities = [RepoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}