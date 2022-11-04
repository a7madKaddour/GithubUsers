package com.ahmadkadddour.githubuser.data.exception

class RequestTimedOutException : Exception {
    constructor()
    constructor(message: String?) : super(message)
    constructor(cause: Throwable?) : super(cause)
}