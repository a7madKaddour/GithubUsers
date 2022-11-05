package com.ahmadkadddour.githubuser.data.sorce.local.database.service

import com.ahmadkadddour.githubuser.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IUsersLocalService {
    fun getAllUsers(): Flow<List<UserModel>>

    fun getUserByName(handle: String): Flow<UserModel>

    suspend fun insertUser(userModel: UserModel)

    suspend fun insertUsers(userModels: List<UserModel>)

    suspend fun deleteAllUsers()
}