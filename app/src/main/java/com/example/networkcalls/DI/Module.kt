package com.example.networkcalls.DI

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.networkcalls.AppDatabase
import com.example.networkcalls.network.Provider
import com.example.networkcalls.repository.JokeRepository
import com.example.networkcalls.repository.dao.RepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{
    @Singleton
    @Provides
    fun providePreferenceModule(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("app", Context.MODE_PRIVATE)
    }
    @Singleton
    @Provides
    fun provideProvider(): Provider {
        return Provider("a56cf1c848mshb468a12ac5efdc7p18c930jsn18008f1bf6d3")

    }
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext appContext: Context): RepoDao {
        val database = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "app-database"
            ).build()
        return database.repoDao()
    }
    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }
    @Singleton
    @Provides
    fun provideJokeRepository(provider : Provider, repodao : RepoDao, context: Context): JokeRepository {

        return JokeRepository(provider, repodao, context)
    }
}
