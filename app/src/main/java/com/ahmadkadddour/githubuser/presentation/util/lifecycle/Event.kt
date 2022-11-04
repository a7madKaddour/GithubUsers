package com.ahmadkadddour.githubuser.presentation.util.lifecycle

class Event<T>(private val data: T) {
    private var isHandled = false

    fun getDataOrNull(): T? {
        return if (isHandled) null
        else {
            isHandled = true
            data
        }
    }
}