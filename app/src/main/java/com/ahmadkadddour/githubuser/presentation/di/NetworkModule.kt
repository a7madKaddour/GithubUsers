package com.ahmadkadddour.githubuser.presentation.di

import com.ahmadkadddour.githubuser.data.exception.factory.ExceptionFactory
import com.ahmadkadddour.githubuser.data.provider.remote.ApiProvider
import com.ahmadkadddour.githubuser.data.provider.remote.IApiProvider
import com.ahmadkadddour.githubuser.data.util.network.networkchecker.NetworkChecker
import com.ahmadkadddour.githubuser.data.util.network.networkchecker.NetworkCheckerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkChecker(checker: NetworkCheckerImpl): NetworkChecker {
        return checker
    }

    @Provides
    @Singleton
    fun providerApiProvider(
        networkChecker: NetworkChecker,
        exceptionFactory: ExceptionFactory
    ): IApiProvider {
        return ApiProvider(networkChecker, exceptionFactory)
    }
}