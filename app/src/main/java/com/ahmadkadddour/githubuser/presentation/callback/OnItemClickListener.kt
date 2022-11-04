package com.ahmadkadddour.githubuser.presentation.callback

fun interface OnItemClickListener<T> {
    fun onClick(item: T, position: Int)
}