package com.ahmadkadddour.githubuser.domain.usecase.base

interface ParamsUseCase<PARAMS, RESULT> {
    suspend fun execute(params: PARAMS): RESULT
}