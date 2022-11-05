package com.ahmadkadddour.githubuser.presentation.di

import com.ahmadkadddour.githubuser.data.repository.UsersRepositoryImpl
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideUsersRepository(repository: UsersRepositoryImpl): IUsersRepository
}