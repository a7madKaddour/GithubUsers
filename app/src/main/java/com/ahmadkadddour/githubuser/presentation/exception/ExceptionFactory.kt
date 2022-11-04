package com.ahmadkadddour.githubuser.presentation.exception

import androidx.annotation.StringRes
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.data.exception.NoInternetException
import com.ahmadkadddour.githubuser.data.exception.RequestTimedOutException
import com.ahmadkadddour.githubuser.data.exception.ServerException
import java.io.IOException
import java.net.SocketTimeoutException

object ExceptionFactory {
    @StringRes
    fun getStringResOf(e: Exception): Int {
        return when (e) {
            is NoInternetException -> R.string.message_please_check_your_internet
            is ServerException, is IOException, is SocketTimeoutException -> R.string.message_something_went_wrong
            is RequestTimedOutException -> R.string.message_request_timed_out
            else -> R.string.message_unknown_error
        }
    }

    @StringRes
    fun getString(throwable: Throwable): Int {
        return (throwable as? Exception)?.let { getStringResOf(it) }
            ?: R.string.message_unknown_error
    }
}