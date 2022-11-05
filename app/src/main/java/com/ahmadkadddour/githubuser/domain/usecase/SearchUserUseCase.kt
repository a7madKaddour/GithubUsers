package com.ahmadkadddour.githubuser.domain.usecase

import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import com.ahmadkadddour.githubuser.domain.usecase.base.coroutine.FlowParamsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val usersRepository: IUsersRepository
) : FlowParamsUseCase<SearchUserUseCase.SearchUserUseCaseParams, UserEntity>{

    override fun execute(params: SearchUserUseCaseParams): Flow<UserEntity> {
        return usersRepository.getUserByName(params.userName).flowOn(Dispatchers.IO)
    }

    data class SearchUserUseCaseParams(
        val userName: String
    )
}