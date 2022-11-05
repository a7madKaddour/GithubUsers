package com.ahmadkadddour.githubuser.data.sorce.remote.api.service.users

import com.ahmadkadddour.githubuser.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IUsersApiService {
    fun getUsers(sinceId: Long? = null): Flow<List<UserModel>>

    fun getUserByUrl(url: String): Flow<UserModel>

    fun getUserByName(userName: String): Flow<UserModel>
}