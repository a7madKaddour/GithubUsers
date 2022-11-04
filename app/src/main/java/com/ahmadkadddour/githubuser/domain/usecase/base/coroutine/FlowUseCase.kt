package com.ahmadkadddour.githubuser.domain.usecase.base.coroutine

import kotlinx.coroutines.flow.*

interface FlowUseCase<RESULT> {
     fun execute(): Flow<RESULT>
}