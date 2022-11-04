package com.ahmadkadddour.githubuser.presentation.ui.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T, DB : ViewDataBinding>(
    protected var binding: DB
) : RecyclerView.ViewHolder(
    binding.root
) {
    abstract fun onBind(item: T, position: Int)
}