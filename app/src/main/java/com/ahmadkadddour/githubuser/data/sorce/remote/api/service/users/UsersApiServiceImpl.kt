package com.ahmadkadddour.githubuser.data.sorce.remote.api.service.users

import com.ahmadkadddour.githubuser.data.model.UserModel
import com.ahmadkadddour.githubuser.data.model.mapper.UserResponseMapper
import com.ahmadkadddour.githubuser.data.provider.remote.IApiProvider
import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.ApiService
import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.RetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersApiServiceImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val userResponseMapper: UserResponseMapper,
    apiProvider: IApiProvider
) : ApiService(apiProvider), IUsersApiService {

    override fun getUsers(sinceId: Long?): Flow<List<UserModel>> {
        return makeRequest { retrofitService.getUsers(sinceId) }.map(userResponseMapper::map)
    }

    override fun getUserByUrl(url: String): Flow<UserModel> {
        return makeRequest { retrofitService.getUserByUrl(url) }.map(userResponseMapper::map)
    }

    override fun getUserByName(userName: String): Flow<UserModel> {
        return makeRequest { retrofitService.getUserByName(userName) }.map(userResponseMapper::map)
    }
}