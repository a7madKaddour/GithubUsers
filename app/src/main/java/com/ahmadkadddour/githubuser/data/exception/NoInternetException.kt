package com.ahmadkadddour.githubuser.data.exception

class NoInternetException : Exception {
    constructor()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}