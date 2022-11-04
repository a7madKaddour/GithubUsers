package com.ahmadkadddour.githubuser.data.exception.factory

import com.ahmadkadddour.githubuser.data.exception.NoInternetException
import com.ahmadkadddour.githubuser.data.exception.ServerException
import com.ahmadkadddour.githubuser.data.util.network.ResponseCode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseExceptionFactory @Inject constructor() : ExceptionFactory {

    override fun fromCode(code: Int, message: String?): Exception {
        return when (code) {
            ResponseCode.NETWORK_ERROR -> NoInternetException(message)
            ResponseCode.SERVER_ERROR -> ServerException(message)
            else -> Exception(message)
        }
    }
}