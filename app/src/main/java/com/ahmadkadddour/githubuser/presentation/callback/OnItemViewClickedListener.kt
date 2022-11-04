package com.ahmadkadddour.githubuser.presentation.callback

import android.view.View

fun interface OnItemViewClickedListener<T> {
    fun onItemClicked(item: T, view: View)
}