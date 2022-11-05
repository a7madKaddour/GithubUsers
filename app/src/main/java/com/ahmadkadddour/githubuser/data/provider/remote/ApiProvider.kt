package com.ahmadkadddour.githubuser.data.provider.remote

import com.ahmadkadddour.githubuser.data.exception.NoInternetException
import com.ahmadkadddour.githubuser.data.exception.RequestTimedOutException
import com.ahmadkadddour.githubuser.data.exception.ServerException
import com.ahmadkadddour.githubuser.data.exception.factory.ExceptionFactory
import com.ahmadkadddour.githubuser.data.util.network.networkchecker.NetworkChecker
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.Response
import java.io.InterruptedIOException

class ApiProvider(
    private val networkChecker: NetworkChecker,
    private val exceptionFactory: ExceptionFactory
) : IApiProvider {

    override fun <T> proceedRequest(call: suspend () -> Response<T>) = flow {
        if (isConnectedOrFail()) {
            val response = getResponseOrFail(call)
            val body = getResponseBodyOrFail(response)
            emit(body)
        }
    }

    private suspend fun isConnectedOrFail(): Boolean {
        if (networkChecker.isConnected()) return true
        throw NoInternetException()
    }

    private suspend fun <T> getResponseOrFail(call: suspend () -> Response<T>): Response<T> {
        return try {
            call.invoke()
        } catch (e: Exception) {
            if (e is InterruptedIOException) throw RequestTimedOutException(e)
            else throw ServerException(e)
        }
    }

    private fun <T> getResponseBodyOrFail(response: Response<T>): T {
        val body = response.body()
        response.message()
        if (response.isSuccessful && body != null) {
            return body
        } else {
            val errorBody = response.errorBody()?.string()
            val message = if (errorBody != null) {
                JSONObject(errorBody)
                    .optString("message", "")
                    .takeIf { it.isNotEmpty() }
            } else null
            throw exceptionFactory.fromCode(response.code(), message ?: response.message())
        }
    }
}