package com.ahmadkadddour.githubuser.domain.usecase.base

interface UseCase<RESULT> {
    suspend fun execute(): RESULT
}
