package com.ahmadkadddour.githubuser.domain.repository

import com.ahmadkadddour.githubuser.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    fun getUsers(sinceId: Long? = null): Flow<List<UserEntity>>

    fun getUserByUrl(url: String): Flow<UserEntity>

    fun getUserByName(userName: String): Flow<UserEntity>
}