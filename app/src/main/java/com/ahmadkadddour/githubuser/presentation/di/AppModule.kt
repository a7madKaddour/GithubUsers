package com.ahmadkadddour.githubuser.presentation.di

import android.content.Context
import android.content.SharedPreferences
import com.ahmadkadddour.githubuser.data.exception.factory.ExceptionFactory
import com.ahmadkadddour.githubuser.data.exception.factory.ResponseExceptionFactory
import com.ahmadkadddour.githubuser.data.provider.local.sharedpreferences.LiveSharedPreferences
import com.ahmadkadddour.githubuser.data.provider.local.sharedpreferences.LiveSharedPreferencesImpl
import com.ahmadkadddour.githubuser.data.util.constants.SharedPreferencesKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharedPreferencesKeys.APP_SHARED_KEY,
            Context.MODE_PRIVATE
        )
    }

    @Singleton
    @Provides
    fun provideLiveSharedPreferences(liveSharedPreferences: LiveSharedPreferencesImpl): LiveSharedPreferences {
        return liveSharedPreferences
    }

    @Singleton
    @Provides
    fun provideExceptionFactory(exceptionFactory: ResponseExceptionFactory): ExceptionFactory {
        return exceptionFactory
    }
}