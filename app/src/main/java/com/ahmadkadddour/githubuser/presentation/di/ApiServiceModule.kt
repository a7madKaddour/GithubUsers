package com.ahmadkadddour.githubuser.presentation.di

import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.users.IUsersApiService
import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.users.UsersApiServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ApiServiceModule {
    @Binds
    @Singleton
    abstract fun provideUsersApiService(service: UsersApiServiceImpl): IUsersApiService
}