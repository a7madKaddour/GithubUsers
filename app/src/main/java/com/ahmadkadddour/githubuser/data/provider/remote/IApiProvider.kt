package com.ahmadkadddour.githubuser.data.provider.remote

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IApiProvider {
    fun <T> proceedRequest(call: suspend () -> Response<T>): Flow<T>
}