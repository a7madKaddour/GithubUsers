package com.ahmadkadddour.githubuser.domain.usecase

import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import com.ahmadkadddour.githubuser.domain.usecase.base.coroutine.FlowParamsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByUrlUseCase @Inject constructor(
    private val usersRepository: IUsersRepository
) : FlowParamsUseCase<GetUserByUrlUseCase.GetUserByUrlUserCaseParams, UserEntity> {

    override fun execute(params: GetUserByUrlUserCaseParams): Flow<UserEntity> {
        return usersRepository.getUserByUrl(params.url)
    }

    data class GetUserByUrlUserCaseParams(
        val url: String
    )
}