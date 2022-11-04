package com.ahmadkadddour.githubuser.presentation.util.lifecycle

import androidx.lifecycle.Observer

class EventObserver<T>(private val onChanged: OnChanged<T>) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>) {
        event.getDataOrNull()?.let {
            onChanged.onChanged(it)
        }
    }

    fun interface OnChanged<T> {
        fun onChanged(data: T)
    }
}