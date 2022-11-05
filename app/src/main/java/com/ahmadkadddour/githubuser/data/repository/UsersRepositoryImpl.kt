package com.ahmadkadddour.githubuser.data.repository

import com.ahmadkadddour.githubuser.data.model.mapper.UserEntityMapper
import com.ahmadkadddour.githubuser.data.sorce.local.database.service.IUsersLocalService
import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.users.IUsersApiService
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersApiService: IUsersApiService,
    private val usersLocalService: IUsersLocalService,
    private val userEntityMapper: UserEntityMapper
) : IUsersRepository {

    override fun getUsers(sinceId: Long?): Flow<List<UserEntity>> {
        return usersApiService.getUsers(sinceId)
            .onEach {
                if (sinceId == null) {
                    usersLocalService.deleteAllUsers()
                }
                usersLocalService.insertUsers(it)
            }
            .catch { error ->
                if (sinceId == null) {
                    emitAll(usersLocalService.getAllUsers())
                } else throw error
            }
            .map(userEntityMapper::map)
    }

    override fun getUserByUrl(url: String): Flow<UserEntity> {
        return usersApiService.getUserByUrl(url)
            .onEach {
                usersLocalService.insertUser(it)
            }
            .map(userEntityMapper::map)
    }

    override fun getUserByName(userName: String): Flow<UserEntity> {
        return usersApiService.getUserByName(userName)
            .onEach {
                usersLocalService.insertUser(it)
            }
            .catch { originalError ->
                emitAll(
                    usersLocalService.getUserByName(userName).catch {
                        throw originalError
                    }
                )
            }
            .map(userEntityMapper::map)
    }
}