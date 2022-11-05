package com.ahmadkadddour.githubuser.data.sorce.local.database.service

import com.ahmadkadddour.githubuser.data.model.UserModel
import com.ahmadkadddour.githubuser.data.model.mapper.LocalUserMapper
import com.ahmadkadddour.githubuser.data.sorce.local.database.dao.UsersDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersLocalServiceImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val localUserMapper: LocalUserMapper
) : IUsersLocalService {
    override fun getAllUsers(): Flow<List<UserModel>> {
        return usersDao.getUsers().map(localUserMapper::map)
    }

    override fun getUserByName(handle: String): Flow<UserModel> {
        return usersDao.getUserByName(handle).map(localUserMapper::map)
    }

    override suspend fun insertUser(userModel: UserModel) {
        usersDao.insertUser(localUserMapper.unmap(userModel))
    }

    override suspend fun insertUsers(userModels: List<UserModel>) {
        usersDao.insertUsers(localUserMapper.unmap(userModels))
    }

    override suspend fun deleteAllUsers() {
        usersDao.deleteAllUsers()
    }
}