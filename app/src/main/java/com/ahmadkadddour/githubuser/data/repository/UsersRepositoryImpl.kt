package com.ahmadkadddour.githubuser.data.repository

import com.ahmadkadddour.githubuser.data.model.mapper.UserEntityMapper
import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.users.IUsersApiService
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersApiService: IUsersApiService,
    private val userEntityMapper: UserEntityMapper
) : IUsersRepository {

    override fun getUsers(sinceId: Long?): Flow<List<UserEntity>> {
        return usersApiService.getUsers(sinceId).map(userEntityMapper::map)
    }

    override fun getUserByUrl(url: String): Flow<UserEntity> {
        return usersApiService.getUserByUrl(url).map(userEntityMapper::map)
    }

    override fun getUserByName(userName: String): Flow<UserEntity> {
        return usersApiService.getUserByName(userName).map(userEntityMapper::map)
    }
}