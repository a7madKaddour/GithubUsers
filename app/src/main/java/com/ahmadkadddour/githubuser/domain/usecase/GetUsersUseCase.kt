package com.ahmadkadddour.githubuser.domain.usecase

import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import com.ahmadkadddour.githubuser.domain.usecase.base.coroutine.FlowParamsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: IUsersRepository
): FlowParamsUseCase<GetUsersUseCase.GetUsersUserCaseParams, List<UserEntity>>{

    override fun execute(params: GetUsersUserCaseParams): Flow<List<UserEntity>> {
        return usersRepository.getUsers(params.sinceId).flowOn(Dispatchers.IO)
    }

    data class GetUsersUserCaseParams(
        val sinceId: Long? = null
    )
}