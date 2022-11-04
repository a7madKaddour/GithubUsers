package com.ahmadkadddour.githubuser.data.exception.factory

interface ExceptionFactory {
    fun fromCode(code: Int, message: String? = null): Exception
}