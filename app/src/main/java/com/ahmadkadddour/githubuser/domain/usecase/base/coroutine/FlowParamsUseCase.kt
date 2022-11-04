package com.ahmadkadddour.githubuser.domain.usecase.base.coroutine

import kotlinx.coroutines.flow.*

interface FlowParamsUseCase<PARAMS, RESULT> {
    fun execute(params: PARAMS): Flow<RESULT>
}