package com.ahmadkadddour.githubuser.presentation.di

import android.content.Context
import androidx.room.Room
import com.ahmadkadddour.githubuser.data.provider.local.database.AppDatabase
import com.ahmadkadddour.githubuser.data.util.constants.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }
}