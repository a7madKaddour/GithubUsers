package com.ahmadkadddour.githubuser.data.sorce.remote.api.service

import com.ahmadkadddour.githubuser.data.provider.remote.IApiProvider
import retrofit2.Response


abstract class ApiService(
    private val apiProvider: IApiProvider
) {

    fun <R> makeRequest(call: suspend () -> Response<R>) = apiProvider.proceedRequest(call)
}