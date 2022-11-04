package com.ahmadkadddour.githubuser.presentation.callback

fun interface OnItemSelectedListener<T> {
    fun onItemSelected(item: T, isSelected: Boolean)
}