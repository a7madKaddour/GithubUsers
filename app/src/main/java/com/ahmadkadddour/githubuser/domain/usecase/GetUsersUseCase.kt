package com.ahmadkadddour.githubuser.domain.usecase

import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import com.ahmadkadddour.githubuser.domain.usecase.base.coroutine.FlowParamsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: IUsersRepository
): FlowParamsUseCase<GetUsersUseCase.GetUsersUserCaseParams, List<UserEntity>>{

    override fun execute(params: GetUsersUserCaseParams): Flow<List<UserEntity>> {
        return usersRepository.getUsers(params.sinceId)
    }

    data class GetUsersUserCaseParams(
        val sinceId: Long? = null
    )
}