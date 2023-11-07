package com.example.networkcalls.DI

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.example.networkcalls.network.Provider
import com.example.networkcalls.repository.JokeRepository
import com.example.networkcalls.ui.SecondFragment
import com.example.networkcalls.usecase.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{
    //@Singleton
    @Provides
    fun providePreferenceModule(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("app", Context.MODE_PRIVATE)
    }
    //@Singleton
    @Provides
    fun provideProvider(): Provider {
        return Provider("a56cf1c848mshb468a12ac5efdc7p18c930jsn18008f1bf6d3")

    }
    //@Singleton
    @Provides
    fun provideJokeRepository(provider : Provider): JokeRepository {

        return JokeRepository(provider)
    }
}

//@Module
//@InstallIn(ActivityComponent::class)
//object ViewModelModule{
//    @Provides
//    fun provideViewModelModule(fragment : SecondFragment) : MyViewModel {
//        return ViewModelProvider(fragment)[MyViewModel::class.java]
//    }
//}
